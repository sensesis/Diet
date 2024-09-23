package com.apple.shop.cream;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service // 이걸 쓰면 해당 serivse를 다른 클래스에서 사용 가능 그렇게 뽑은 서비스를 빈이라고 부름
@RequiredArgsConstructor
public class ClothService {

    private final ClothRepository clothRepository;
    private final MemberRepository memberRepositoiry;

    public void saveCloth(String name, String type) {

        Cloth cloth = new Cloth();

        cloth.setName(name);
        cloth.setType(type);

        clothRepository.save(cloth);

    }

    public void updateCloth(Long id, String name, String type) {

       Optional<Cloth> cloth = clothRepository.findById(id);
        //Optional<T>는 특정값 있을수도 있고 없을 수도 있는 상황 처리하기 위해서 사용됨.
        //null 방지, 안전하게 다룰 수 있게 해줌

       if (cloth.isPresent()) {

           Cloth c = cloth.get(); // c에다가 그 값을 반환한다
           c.setName(name);
           c.setType(type);

           clothRepository.save(c);
       }

       else
           throw new RuntimeException("아이디를 못찾겠어요. id: " + id);
    }

    public void searchDetail(@PathVariable Long id, Model model) {

        Cloth result = clothRepository.findById(id).orElse(null);
        model.addAttribute("detail", result);
    }
}
