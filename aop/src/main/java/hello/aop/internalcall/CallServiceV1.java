package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    /**
     * 강의와 달리 setter 주입도 순환참조 오류 발생 -> 대안2 지연 조회 방법을 사용
     */
//    private CallServiceV1 callServiceV1;
    private final ObjectProvider<CallServiceV1> callServiceV1Provider;

    public CallServiceV1(ObjectProvider<CallServiceV1> callServiceV1Provider) {
        this.callServiceV1Provider = callServiceV1Provider;
    }

    //    @Autowired
//    public void setCallServiceV1(CallServiceV1 callServiceV1) {
//        // 생성자를 사용하면 순환참조 문제가 발생하므로 setter로 주입한다.
//        log.info("callServiceV1 setter={}", callServiceV1.getClass());
//        this.callServiceV1 = callServiceV1;
//    }

    public void external() {
        log.info("call external");
        callServiceV1Provider.getObject().internal();
    }

    public void internal() {
        log.info("call internal");
    }
}
