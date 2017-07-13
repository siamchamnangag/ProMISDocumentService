package com.scg.document.controller;

import com.scg.document.model.SAPUser;
import com.scg.document.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tanatloke on 7/12/2017.
 */
@RestController()
@RequestMapping("documents")
public class DocumentController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/{doc_id}")
    ResponseEntity createDocumentFromTemplate(@PathVariable("doc_id") int documentId, @RequestParam("xomLanID")String xomLanID){

        //get sap user
        SAPUser sapUser;
        //validate user
        try {
           sapUser  = userService.getSAPUser(xomLanID);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("user not found",HttpStatus.BAD_REQUEST);
        }

        //get dir object {description status user url} by docid
        //open file with link from dir
        //upload  file and description to files and get back new link
        //create dir with new link and old dir
        //return dir and message to user

        return new ResponseEntity(sapUser,HttpStatus.OK);
    }


}