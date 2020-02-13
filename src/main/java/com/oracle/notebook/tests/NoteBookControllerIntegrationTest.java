package com.oracle.notebook.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.notebook.NotebookApplication;
import com.oracle.notebook.controller.dto.CodeDto;
import com.oracle.notebook.controller.dto.ResultDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NotebookApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteBookControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testExecutePythonCode() throws Exception {

        String uri = "/oracle/notebook/execute";
        String code = "%python print 'hello'";
        CodeDto codeDto = new CodeDto(code);

        ResultDto expectedResult = new ResultDto("hello");
        String expected = objectMapper.writeValueAsString(expectedResult);

        HttpEntity<CodeDto> dto = new HttpEntity<>(codeDto, headers);
        ResponseEntity<ResultDto> response = restTemplate.exchange(
                createURLWithPort(uri),
                HttpMethod.POST, dto, ResultDto.class);

        ResultDto actual = response.getBody() != null ? response.getBody() : new ResultDto();

        JSONAssert.assertEquals(expected, objectMapper.writeValueAsString(actual), true);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
