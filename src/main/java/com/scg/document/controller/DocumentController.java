package com.scg.document.controller;

import com.scg.document.model.DirDTO;
import com.scg.document.model.SAPUser;
import com.scg.document.model.SCGResponseBody;
import com.scg.document.model.UploadFileDTO;
import com.scg.document.service.DocumentService;
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

    @Autowired
    DocumentService documentService;

    @RequestMapping(method = RequestMethod.POST, value = "/{doc_id}")
    ResponseEntity createDocumentFromTemplatev1(@PathVariable("doc_id") int documentId){

        //get dir object {description status user url} by docid
        DirDTO dir;

        try {
           dir  = documentService.getDirByDocID(documentId);
        } catch (Exception e) {
           return new ResponseEntity(new SCGResponseBody("document is not found"),HttpStatus.NOT_FOUND);
        }

        //open file with link from dir
        byte[] fileContent;
        try {
            fileContent = documentService.getFileContent(dir.getLink()).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new SCGResponseBody("upload failed"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //upload  file and description to files and get back new link
        UploadFileDTO uploadFileDTO;

        try {
            uploadFileDTO = (UploadFileDTO) documentService.uploadFileContent(dir.getLink(),fileContent,dir.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new SCGResponseBody("upload failed"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //
        // create dir with new link and old dir

        //return dir and message to user
        return new ResponseEntity(uploadFileDTO.getLink(),HttpStatus.OK);
    }

    /*@RequestMapping(method = RequestMethod.POST, value = "/{doc_id}")
    ResponseEntity createDocumentFromTemplatev2(@PathVariable("doc_id") int documentId, @RequestParam("xomLanID")String xomLanID){

      *//*
        //get sap user
        SAPUser sapUser;
        //validate user
        try {
           sapUser  = userService.getSAPUser(xomLanID);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("user not found",HttpStatus.BAD_REQUEST);
        }*//*

        //get dir object {description status user url} by docid
        //open file with link from dir
        //upload  file and description to files and get back new link
        //create dir with new link and old dir
        //return dir and message to user

        return new ResponseEntity(sapUser,HttpStatus.OK);
    }*/
}