package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }
    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam(required=false) String searchTerm, @RequestParam String searchType){
       ArrayList <Job> jobs;
       // (Case 1) searchType = all, searchTerm = anything
        // (case 2) searchType = all, searchTerm = null
        // (case 3) searchType = location searchTerm = anything
        if(searchType.toLowerCase().equals("all") && searchTerm != null){
            jobs = JobData.findByValue(searchTerm);
            model.addAttribute("searchType", searchType);
            model.addAttribute("searchTerm", searchTerm);
            model.addAttribute("columns", columnChoices);
            model.addAttribute("jobs", jobs);

        } else if (searchType.toLowerCase().equals("all") || searchType.equals(" ")){
          jobs = JobData.findAll();
           model.addAttribute("searchType", searchType);
           model.addAttribute("columns", columnChoices);
           model.addAttribute("jobs", jobs);
       } else {
           jobs = JobData.findByColumnAndValue(searchType, searchTerm);
           model.addAttribute("searchTerm", searchTerm);
           model.addAttribute("columns", columnChoices);
           model.addAttribute("jobs", jobs);
       }

        return "search";
    }


}

    // TODO #3 - Create a handler to process a search request and render the updated search view.


