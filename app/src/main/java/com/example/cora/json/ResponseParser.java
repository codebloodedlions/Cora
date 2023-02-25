package com.example.cora.json;

import com.example.cora.api.Models.completions.cRoot;
import com.example.cora.api.Models.edits.eRoot;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseParser {
    public static cRoot parseCompletionJSON(String response){
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(response, cRoot.class);
        }
        catch (Exception ex){
            ex.printStackTrace();
            System.exit(500);
            return null;
        }
    }

    public static eRoot parseEditJSON(String response){
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(response, eRoot.class);
        }
        catch (Exception ex){
            ex.printStackTrace();
            System.exit(500);
            return null;
        }
    }
}


