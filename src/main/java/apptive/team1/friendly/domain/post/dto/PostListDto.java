package apptive.team1.friendly.domain.post.dto;

import apptive.team1.friendly.domain.post.entity.HashTag;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PostListDto {
    // 게시판 리스트에 보이는 필드
    private String title;

    private int maxPeople;

    private LocalDateTime promiseTime;

    private String location;

    private List<HashTag> hashTag = new ArrayList<HashTag>();

    private String image;

}
