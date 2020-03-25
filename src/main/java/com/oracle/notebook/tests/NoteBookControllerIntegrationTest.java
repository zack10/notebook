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

    private ResultDto sendRequestToServer(String code) {
        CodeDto codeDto = new CodeDto(code);

        HttpEntity<CodeDto> dto = new HttpEntity<>(codeDto, headers);
        ResponseEntity<ResultDto> response = restTemplate.exchange(
                createURLWithPort(),
                HttpMethod.POST, dto, ResultDto.class);

        return response.getBody() != null ? response.getBody() : new ResultDto();
    }

    @Test
    public void testPrintString() throws Exception {
        String code = "%python print 'hello'";
        ResultDto expectedResult = new ResultDto("hello");

        String expected = objectMapper.writeValueAsString(expectedResult);
        ResultDto actual = sendRequestToServer(code);

        JSONAssert.assertEquals(expected, objectMapper.writeValueAsString(actual), true);
    }

    @Test
    public void assignVariableThenPrintIt() throws Exception {
        String code = "%python a=5";
        ResultDto expectedResult = new ResultDto("10");

        String expected = objectMapper.writeValueAsString(expectedResult);
        ResultDto result = sendRequestToServer(code);

        String code2 = "%python print ";
        ResultDto actual2 = sendRequestToServer(code2+result.getResult()+"*2");

        JSONAssert.assertEquals(expected, objectMapper.writeValueAsString(actual2), true);
    }

    private String createURLWithPort() {
        String uri = "/oracle/notebook/execute";
        return "http://localhost:" + port + uri;
    }
}
