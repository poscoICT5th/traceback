package Pack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Pack.vo.TestVo;

@Service
public class TestService {
    @Autowired

    public List<TestVo> selectTest() {
        return null;
    }
}
