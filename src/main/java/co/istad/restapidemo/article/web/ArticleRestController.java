package co.istad.restapidemo.article.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/articles")
public class ArticleRestController {

@GetMapping
        List<ArticleDTO> findAllArticle(){
    List<ArticleDTO> articleDTOList = new ArrayList<>(){{
        add(new ArticleDTO(1,"ABC"));
        add(new ArticleDTO(12,"Anchor"));


    }};
    return articleDTOList;
}

}
