package com.albo.marvel.service.impl;

import com.albo.marvel.exceptionhandling.CustomException;
import com.albo.marvel.model.dto.Colorists;
import com.albo.marvel.model.dto.Editors;
import com.albo.marvel.model.dto.Writers;
import com.albo.marvel.model.entity.*;
import com.albo.marvel.model.marvel.DataCharacters;
import com.albo.marvel.model.marvel.RootCharacters;
import com.albo.marvel.model.marvel.RootComics;
import com.albo.marvel.model.response.CharactersResponse;
import com.albo.marvel.model.response.ColabratorsResponse;
import com.albo.marvel.repository.MarvelCharactersRepository;
import com.albo.marvel.repository.MarvelLastSyncRepository;
import com.albo.marvel.service.MarvelService;
import com.albo.marvel.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MarvelServiceImpl implements MarvelService {

	private static final Logger logger = LoggerFactory.getLogger(MarvelServiceImpl.class);

	private final MarvelCharactersRepository charactersRepository;
	private final MarvelLastSyncRepository lastSyncRepository;

	@Value("${marvel.url}")
	private String marvelUrl;
	@Value("${marvel.public.key}")
	private String marvelPubliKey;
	@Value("${marvel.private.key}")
	private String marvelPrivateKey;

	@Autowired
	public MarvelServiceImpl(MarvelCharactersRepository productRepository, MarvelLastSyncRepository lastSyncRepository) {
		this.charactersRepository = productRepository;
		this.lastSyncRepository = lastSyncRepository;
	}


	@Override
	public ColabratorsResponse getMarvelColaborators(String name){

		ColabratorsResponse colaborator = new ColabratorsResponse();

		try {

			List<String> editors = new ArrayList<>();
			List<String> writers = new ArrayList<>();
			List<String> colorists = new ArrayList<>();

			List<Characters> characterList = charactersRepository.findByName(name);

			characterList.forEach(_character -> {

				_character.getComics().forEach(_comic -> {

					_comic.getCreatorsList().forEach(_colaborator -> {

						Editors editor = new Editors();
						Writers writer = new Writers();
						Colorists colorist = new Colorists();

						switch (_colaborator.getType())
						{
							case "colorist":
								if(!colorists.contains(_colaborator.getName())){
									colorist.setName(_colaborator.getName());
									colorists.add(colorist.getName());
								}
								break;
							case "writer":
								if(!writers.contains(_colaborator.getName())){
									writer.setName(_colaborator.getName());
									writers.add(writer.getName());
								}
								break;
							case "editor":
								if(!editors.contains(_colaborator.getName())){
									editor.setName(_colaborator.getName());
									editors.add(editor.getName());
								}
								break;

						}
					});

				});

			});

			LastSync lastSync = lastSyncRepository.findTopByOrderByIdDesc();
			if(lastSync != null)
			{
				colaborator.setLastSync(lastSync.getDate());
				colaborator.setEditors(editors);
				colaborator.setWriters(writers);
				colaborator.setColorists(colorists);
			}
			return colaborator;

		} catch (Exception e) {
			throw new CustomException(CharactersResponse.class, "Error", e.toString());
		}
	}

	@Override
	public CharactersResponse getMarvelCharacters(String name){

		try {
			CharactersResponse charactersRespose = new CharactersResponse();

			List<Characters> characterList = charactersRepository.findByName(name);

			List<String> inList = new ArrayList<>();

			List<com.albo.marvel.model.dto.Characters> characters = new ArrayList<>();

			characterList.forEach(_character -> {

				_character.getComics().forEach(_comics -> {

					_comics.getColaboratorsLits().forEach(_colaborators -> {

						if(!inList.contains(_colaborators.getName()) && !_colaborators.getName().equals(name)){

							List<String> comicsList = new ArrayList<>();

							List<Characters> characterSubList= charactersRepository.findByName(_colaborators.getName());
							characterSubList.forEach(_characterSubList -> {

								_characterSubList.getComics().forEach(_comicsSubList -> {
									String comic = _comicsSubList.getTitle();
									if(!comicsList.contains(comic)){
										comicsList.add(comic);
									}
								});

							});

							com.albo.marvel.model.dto.Characters colaborators = new com.albo.marvel.model.dto.Characters();
							colaborators.setCharacter(_colaborators.getName());
							colaborators.setComics(comicsList);

							characters.add(colaborators);
							inList.add(_colaborators.getName());

						}

					});
				});

			});

			LastSync lastSync = lastSyncRepository.findTopByOrderByIdDesc();
			if(lastSync != null) {
				charactersRespose.setLastSync(lastSync.getDate());
				charactersRespose.setCharacters(characters);
			}

			return charactersRespose;

		} catch (Exception e) {
			throw new CustomException(CharactersResponse.class, "Error", e.toString());
		}
	}

	@Async
	public String sync(){

		try {

			String hash = MD5Util.hash(marvelPubliKey, marvelPrivateKey);

			RestTemplate restCharacters = new RestTemplate();
			String urlCharacters = marvelUrl + "characters?" + hash + "&limit=1";
			//Create a call with limit = 1 to get total of comics and make call for each offset
			RootCharacters api = restCharacters.getForObject(urlCharacters, RootCharacters.class);

			DataCharacters data = api.getData();
			Integer offset = data.getOffset();
			Integer total = data.getTotal();

			logger.info("Start Sync");
			while (true) {

				urlCharacters = marvelUrl + "characters?" + hash + "&limit=" + 100 + "&offset=" + offset;
				RootCharacters apiCallCharacters = restCharacters.getForObject(urlCharacters, RootCharacters.class);

				List<Characters> objectCharacters = new ArrayList<>();
				apiCallCharacters.getData().getResults().forEach(_character -> {

					boolean isInDb = false;

					Characters isCharacter = charactersRepository.findTopByUid(_character.getId());
					if(isCharacter != null){
						isInDb = true;
					}

					if(!isInDb){

						Characters character = new Characters();
						character.setUid(_character.getId());
						character.setName(_character.getName());

						String urlComics = marvelUrl + "characters/" + _character.getId() + "/comics?" + hash + "&limit=" + 100;
						RootComics apiCallComics = restCharacters.getForObject(urlComics, RootComics.class);

						List<Comics> comicsList = new ArrayList<>();
						apiCallComics.getData().getResults().forEach(_comics -> {

							Comics comic = new Comics();
							comic.setTitle(_comics.getTitle());
							comic.setCharacters(character);

							List<Creators> creatorsList = new ArrayList<>();
							_comics.getCreatorsComics().getItems().forEach(_creators -> {

								Creators creators = new Creators();
								creators.setName(_creators.getName());
								creators.setType(_creators.getRole());
								creators.setComics(comic);
								creatorsList.add(creators);

							});

							List<Colaborators> colaboratorsList = new ArrayList<>();
							_comics.getColaboratorsComics().getItems().forEach(_colaborators -> {

								Colaborators colaborators = new Colaborators();
								colaborators.setName(_colaborators.getName());
								colaborators.setComics(comic);
								colaboratorsList.add(colaborators);

							});

							comic.setCreatorsList(creatorsList);
							comic.setColaboratorsLits(colaboratorsList);

							comicsList.add(comic);

						});

						character.setComics(comicsList);

						charactersRepository.save(character);

						LastSync lastSync = new LastSync();
						lastSync.setDate(new Date());
						lastSyncRepository.save(lastSync);

						logger.info("Last saving comics data from endpoint at " + new Date());
					}


				});

				if (total < 100) {
					break;
				}

				offset += 100;
				total -= 100;

				logger.info("TOTAL: " + total);

			}
			logger.info("End Sync");

			return "Data Sinced";

		} catch (Exception e) {

			return "Error in syncing try again";
		}
	}

	public String delete(){

		try {

			charactersRepository.deleteAll();
			lastSyncRepository.deleteAll();

			return "Data deleted";

		} catch (Exception e) {

			return "Error deletig data try again";
		}
	}


}
