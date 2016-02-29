/*
 * Copyright (c) Duc Nguyen Van. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.mycompany.superhero.controller;

import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.method.HandlerMethod;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

/**
 * Base class for controller tests.
 *
 * @author duc
 */
public abstract class BaseControllerTest {

    protected void testRequestMapping(final HttpMethod httpMethod, final String requestMapping, final String methodName)
            throws Exception {

        testRequestMapping(httpMethod, requestMapping, methodName, false);
    }

    protected void testRequestMappingWithFileUpload(final HttpMethod httpMethod, final String requestMapping,
                                                    final String methodName) throws Exception {

        testRequestMapping(httpMethod, requestMapping, methodName, true);
    }

    private void testRequestMapping(final HttpMethod httpMethod, final String requestMapping, final String methodName,
                                    final boolean fileUpload) throws Exception {

        final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(getController()).build();

        final MockHttpServletRequestBuilder requestBuilder =
                fileUpload ? constructFileUploadRequestBuilder(requestMapping) :
                               constructRequestBuilder(httpMethod, requestMapping);

        final HandlerMethod handler =
                (HandlerMethod) mockMvc.perform(requestBuilder).andReturn().getHandler();

        assertThat(handler.getMethod().getName(), equalTo(methodName));
    }

    private MockHttpServletRequestBuilder constructRequestBuilder(final HttpMethod httpMethod,
                                                                  final String requestMapping) {
        final MockHttpServletRequestBuilder requestBuilder =
                POST == httpMethod ? post(requestMapping) :
                DELETE == httpMethod ? delete(requestMapping) :
                PUT == httpMethod ? put(requestMapping) : get(requestMapping);

        return requestBuilder;
    }

    private MockHttpServletRequestBuilder constructFileUploadRequestBuilder(final String requestMapping) {
        final MockMultipartFile file = new MockMultipartFile("file", "original_file",
                                                        "multipart/form-data; boundary=123", "content".getBytes(UTF_8));

        return fileUpload(requestMapping).file(file).contentType(file.getContentType());
    }

    protected abstract Object getController();
}
