package guru.springframework.spring5recipeapp.Controller;

import guru.springframework.spring5recipeapp.Services.ImageService;
import guru.springframework.spring5recipeapp.Services.RecipeService;
import guru.springframework.spring5recipeapp.command.RecipeCommand;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {
    private ImageService imageService;
    private RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }
    @GetMapping("recipe/{id}/image")
    public String ChangeImage(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findRecipeCommandById(Long.valueOf(id)));

        return "recipe/imageuploadform";
    }
//save image into db
@PostMapping("recipe/{id}/image")
    public String SaveImage(@PathVariable String id, @RequestParam("imagefile")MultipartFile file) throws IOException {
        imageService.save(Long.valueOf(id),file);
        return "redirect:/recipe/show/"+id;
    }
////get images from db and set intoimage
    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = (RecipeCommand) recipeService.findRecipeCommandById(Long.valueOf(id));

        if (recipeCommand.getImages() != null) {
            byte[] byteArray = new byte[recipeCommand.getImages().length];
            int i = 0;

            for (Byte wrappedByte : recipeCommand.getImages()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);

            //convert input streem to output
            IOUtils.copy(is, response.getOutputStream());
    }

}}
