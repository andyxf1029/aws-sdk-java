/*
 * Copyright 2012-2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with
 * the License. A copy of the License is located at
 * 
 * http://aws.amazon.com/apache2.0
 * 
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package com.amazonaws.services.iot.model.transform;

import java.math.*;

import javax.annotation.Generated;

import com.amazonaws.services.iot.model.*;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers.*;
import com.amazonaws.transform.*;

import com.fasterxml.jackson.core.JsonToken;
import static com.fasterxml.jackson.core.JsonToken.*;

/**
 * OTAUpdateFile JSON Unmarshaller
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class OTAUpdateFileJsonUnmarshaller implements Unmarshaller<OTAUpdateFile, JsonUnmarshallerContext> {

    public OTAUpdateFile unmarshall(JsonUnmarshallerContext context) throws Exception {
        OTAUpdateFile oTAUpdateFile = new OTAUpdateFile();

        int originalDepth = context.getCurrentDepth();
        String currentParentElement = context.getCurrentParentElement();
        int targetDepth = originalDepth + 1;

        JsonToken token = context.getCurrentToken();
        if (token == null)
            token = context.nextToken();
        if (token == VALUE_NULL) {
            return null;
        }

        while (true) {
            if (token == null)
                break;

            if (token == FIELD_NAME || token == START_OBJECT) {
                if (context.testExpression("fileName", targetDepth)) {
                    context.nextToken();
                    oTAUpdateFile.setFileName(context.getUnmarshaller(String.class).unmarshall(context));
                }
                if (context.testExpression("fileVersion", targetDepth)) {
                    context.nextToken();
                    oTAUpdateFile.setFileVersion(context.getUnmarshaller(String.class).unmarshall(context));
                }
                if (context.testExpression("fileSource", targetDepth)) {
                    context.nextToken();
                    oTAUpdateFile.setFileSource(StreamJsonUnmarshaller.getInstance().unmarshall(context));
                }
                if (context.testExpression("codeSigning", targetDepth)) {
                    context.nextToken();
                    oTAUpdateFile.setCodeSigning(CodeSigningJsonUnmarshaller.getInstance().unmarshall(context));
                }
                if (context.testExpression("attributes", targetDepth)) {
                    context.nextToken();
                    oTAUpdateFile.setAttributes(new MapUnmarshaller<String, String>(context.getUnmarshaller(String.class), context
                            .getUnmarshaller(String.class)).unmarshall(context));
                }
            } else if (token == END_ARRAY || token == END_OBJECT) {
                if (context.getLastParsedParentElement() == null || context.getLastParsedParentElement().equals(currentParentElement)) {
                    if (context.getCurrentDepth() <= originalDepth)
                        break;
                }
            }
            token = context.nextToken();
        }

        return oTAUpdateFile;
    }

    private static OTAUpdateFileJsonUnmarshaller instance;

    public static OTAUpdateFileJsonUnmarshaller getInstance() {
        if (instance == null)
            instance = new OTAUpdateFileJsonUnmarshaller();
        return instance;
    }
}