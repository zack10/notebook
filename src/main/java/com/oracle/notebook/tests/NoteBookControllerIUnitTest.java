package com.oracle.notebook.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.notebook.controller.NoteBookController;
import com.oracle.notebook.controller.dto.CodeDto;
import com.oracle.notebook.service.IPythonInterpreterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = NoteBookController.class)
public class NoteBookControllerIUnitTest extends AbstractTest{

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IPythonInterpreterService pythonInterpreterService;


    @Before
    @Override
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void execute() throws Exception {
        String code = "%python print 'hello'";
        CodeDto codeDto = new CodeDto(code);
        mvc.perform(post("/notebook/execute")
                .contentType(MediaType.APPLICATION_JSON)
                .session(new MockHttpSession())
                .accept(MediaType.APPLICATION_JSON)
                .content(mapToJson(codeDto)))
                .andExpect(status().isOk()) ;
    }
}
