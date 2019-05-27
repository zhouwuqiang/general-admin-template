package com.java.business.wordbook.controller;

import com.java.business.wordbook.facade.WordbookFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/27 16:54
 */
@RestController
@RequestMapping("/wordbook")
public class WordbookController {


    @Autowired
    private WordbookFacade wordbookFacade;

}
