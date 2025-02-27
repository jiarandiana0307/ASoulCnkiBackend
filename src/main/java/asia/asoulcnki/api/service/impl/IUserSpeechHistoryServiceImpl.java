package asia.asoulcnki.api.service.impl;

import asia.asoulcnki.api.persistence.entity.UserSpeechHistoryList;
import asia.asoulcnki.api.persistence.vo.UserSpeechHistoryVO;
import asia.asoulcnki.api.service.IUserSpeechHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class IUserSpeechHistoryServiceImpl implements IUserSpeechHistoryService {
    private static final Logger logger = LoggerFactory.getLogger(IUserSpeechHistoryServiceImpl.class);

    @Override
    public UserSpeechHistoryVO getHistory(Long mid) {
        UserSpeechHistoryList db = UserSpeechHistoryList.getInstance();
        return db.get(mid);
    }
}
