package nus.iss.day17.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import nus.iss.day17.model.GiphyImage;
import nus.iss.day17.service.GiphyService;

@Controller
@RequestMapping
public class GiphyController {

    @Autowired
    GiphyService svc;

    @PostMapping(path="/search")
    public ModelAndView getSearch(@RequestParam String searchString) {
        ModelAndView mav = new ModelAndView("display");
        List<GiphyImage> imageList = svc.search(searchString);
        mav.addObject("list", imageList);
        return mav;
    }
    
}
