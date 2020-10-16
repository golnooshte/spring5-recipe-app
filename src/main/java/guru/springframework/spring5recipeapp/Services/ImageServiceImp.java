package guru.springframework.spring5recipeapp.Services;

import guru.springframework.spring5recipeapp.Repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Slf4j
@Service
public class ImageServiceImp implements ImageService {
    RecipeRepository recipeRepository;

    public ImageServiceImp(RecipeRepository recipeService) {
        this.recipeRepository = recipeService;
    }

    @Override
    public void save(Long id, MultipartFile file)  {
        try{
        Recipe recipe= recipeRepository.findById(id).get();
        byte[] imagebytes=new byte[file.getBytes().length];
        int i=0;
        for (byte b: file.getBytes()){
            imagebytes[i++]=b;
        }
        recipe.setImages(imagebytes);
        recipeRepository.save(recipe);



    }catch (IOException e){
            System.out.println("couldnot save image"+e.getMessage());
e.printStackTrace();
        }
}}
