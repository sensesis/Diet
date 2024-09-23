package com.apple.shop.cream;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class ClothController {

    private final ClothService clothService;
    private final ClothRepository clothRepository;

//    @Autowired
//    public ClothController(ClothRepository clothRepository, ClothService clothService) {
//        this.clothRepository = clothRepository;
//        this.clothService = clothService;
//    }

    @GetMapping("/cloth")
    public String cloth(Model model) {

        List<Cloth> result = clothRepository.findAll();
        model.addAttribute("cloths", result);

        return "cloth.html";
    }

    @GetMapping("/write")
    public String write() {

        return "write.html";
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable Long id, Model model) {
        Optional<Cloth> result = clothRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("data", result.get());
            return "edit.html";
        } else {
            return "redirect:/list";
        }
    }

//    @DeleteMapping("/delete/{id}")
//    String delete(@PathVariable Long id, Model model) {
//
//    }

    @PostMapping("/edit")
    public String editItem(Long id, String name, String type) {

        Cloth cloth = new Cloth();
        cloth.setId(id);
        cloth.setName(name);
        cloth.setType(type);
        clothRepository.save(cloth);

        return "redirect:/cloth";
    }

    @PostMapping("/add")
    public String addPost(String name, String type) {

        clothService.saveCloth(name, type);
        // 저장 후 리다이렉트
        return "redirect:/cloth";
    }

    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable Long id, @RequestParam String name, @RequestParam String type) {

        clothService.updateCloth(id, name, type);
        // 저장 후 리다이렉트
        return "redirect:/cloth";
    }

    @DeleteMapping("/cloth/{id}")
    ResponseEntity<String> deleteCloth(@PathVariable Long id) {
        // ID로 엔티티 삭제
        clothRepository.deleteById(id);

        // 성공적으로 삭제되었을 때 응답
        return ResponseEntity.status(200).body("삭제완료");
    }

    @GetMapping("/test2")
    String test() {

        var encoder = new BCryptPasswordEncoder();
        System.out.println("ㅎㅇ");
        System.out.println(encoder.encode("123456"));
        return "redirect:/cloth";
    }

    @GetMapping("/my-page")
    public String myPage(Authentication auth) {

        System.out.println(auth);

        return "mypage";
    }

    @GetMapping("/test/1")
    public Data test2() {

        var a = clothRepository.findById(1L);

        var data = new Data("sdf");
        data.email = a.get().getName();

        return data;

    }

    class Data {

        public String email;

        Data(String email) {
            this.email = email;
        }
    }

//    @GetMapping("/cloth/page/{abc}")
//    String getClothPage(Model model, @PathVariable Integer abc) {
//
//        List<Cloth> result = clothRepository.findPageBy(PageRequest.of(abc-1, 5));
//        model.addAttribute("data", result);
//        return "cloth.html";
//    }

}

