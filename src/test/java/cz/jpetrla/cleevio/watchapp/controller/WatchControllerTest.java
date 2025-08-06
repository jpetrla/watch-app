package cz.jpetrla.cleevio.watchapp.controller;

import cz.jpetrla.cleevio.watchapp.model.Watch;
import cz.jpetrla.cleevio.watchapp.service.WatchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WatchController.class)
class WatchControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private WatchService service;

    @Test
    void testUpload_json_created() throws Exception {
        String content = readRequestContent("WatchControllerTest/upload-created.json");

        mvc.perform(post("/api/v1/watch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(service, times(1)).upload(any(Watch.class));
    }

    @Test
    void testUpload_xml_created() throws Exception {
        String content = readRequestContent("WatchControllerTest/upload-created.xml");

        mvc.perform(post("/api/v1/watch")
                        .contentType(MediaType.APPLICATION_XML)
                        .content(content))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(service, times(1)).upload(any(Watch.class));
    }

    @Test
    void testUpload_UnsupportedMediaType() throws Exception {
        mvc.perform(post("/api/v1/watch")
                        .contentType(MediaType.TEXT_PLAIN_VALUE)
                        .content(""))
                .andDo(print())
                .andExpect(status().isUnsupportedMediaType());

        verify(service, times(0)).upload(any(Watch.class));
    }

    @Test
    void testUpload_badRequest_missingContent() throws Exception {
        mvc.perform(post("/api/v1/watch")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;

        verify(service, times(0)).upload(any(Watch.class));
    }

    @Test
    void testUpload_badRequest_emptyJson() throws Exception {
        String content = readRequestContent("WatchControllerTest/upload-bad_request-empty.json");

        mvc.perform(post("/api/v1/watch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(service, times(0)).upload(any(Watch.class));
    }

    @Test
    void testUpload_badRequest_nullValues() throws Exception {
        String content = readRequestContent("WatchControllerTest/upload-bad_request-null_values.json");

        mvc.perform(post("/api/v1/watch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(service, times(0)).upload(any(Watch.class));
    }

    @Test
    void testUpload_badRequest_emptyValues() throws Exception {
        String content = readRequestContent("WatchControllerTest/upload-bad_request-empty_values.json");

        mvc.perform(post("/api/v1/watch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(service, times(0)).upload(any(Watch.class));
    }

    @Test
    void testUpload_badRequest_ZeroPrice() throws Exception {
        String content = readRequestContent("WatchControllerTest/upload-bad_request-zero_price.json");

        mvc.perform(post("/api/v1/watch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(service, times(0)).upload(any(Watch.class));
    }

    @Test
    void testUpload_badRequest_InvalidPrice() throws Exception {
        String content = readRequestContent("WatchControllerTest/upload-bad_request-invalid_price.json");

        mvc.perform(post("/api/v1/watch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(service, times(0)).upload(any(Watch.class));
    }

    @Test
    void testUpload_badRequest_invalidBase64() throws Exception {
        String content = readRequestContent("WatchControllerTest/upload-bad_request-invalid_base64.json");

        mvc.perform(post("/api/v1/watch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(service, times(0)).upload(any(Watch.class));
    }

    private String readRequestContent(String path) throws IOException {
        Resource resource = new ClassPathResource(path);
        File file = resource.getFile();
        return new String(Files.readAllBytes(file.toPath()));
    }
}
