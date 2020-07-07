package com.mysteel.tao.banji;

import com.mysteel.tao.banji.entity.Clazz;
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
public class ClazzTest {


    @Autowired
    private WebTestClient webClient;


    @Test
    public void testSave() throws Exception {
        Clazz teacher = new Clazz();
        teacher.setName("test");


     /*   Clazz ts = webClient.post().uri("/clazz/")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(teacher))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Clazz.class).returnResult().getResponseBody();

        Clazz t = webClient.get().uri("/clazz/" + ts.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Clazz.class).returnResult().getResponseBody();
        System.out.println(t);*/

    }

    @Test
    public void findAll1() {
        Teacher teacher = new Teacher();
        teacher.setName("test");


        List<Map> t = webClient.get().uri("/clazz/")
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class).returnResult().getResponseBody();
        System.out.println(t);


        for (Map teacher1 : t) {
            webClient.delete().uri("/clazz/" + teacher1.get("id"))
                    .exchange()
                    .expectStatus().isOk();
        }

        List ts = webClient.get().uri("/clazz/")
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class).returnResult().getResponseBody();
        System.out.println(ts);
    }
}
