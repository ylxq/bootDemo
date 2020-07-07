package com.mysteel.tao.laoshi;

import com.mysteel.tao.laoshi.eneity.Teacher;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@AutoConfigureWebTestClient(timeout = "36000")
public class TeacherTest {


    @Autowired
    private WebTestClient webClient;


    @Test
    public void testSave() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setName("test");


       /* Teacher ts = webClient.post().uri("/teacher/")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(teacher))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Teacher.class).returnResult().getResponseBody();

        Teacher t = webClient.get().uri("/teacher/" + ts.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Teacher.class).returnResult().getResponseBody();
        System.out.println(t);*/

    }

    @Test
    public void findAll1() {
        Teacher teacher = new Teacher();
        teacher.setName("test");


        List<Map> t = webClient.get().uri("/teacher/")
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class).returnResult().getResponseBody();
        System.out.println(t);


        for (Map teacher1 : t) {
            webClient.delete().uri("/teacher/" + teacher1.get("id"))
                    .exchange()
                    .expectStatus().isOk();
        }

        List ts = webClient.get().uri("/teacher/")
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class).returnResult().getResponseBody();
        System.out.println(ts);
    }
}
