package apptive.team1.friendly.domain.post.service;

import apptive.team1.friendly.domain.post.dto.PostDto;
import apptive.team1.friendly.domain.post.dto.PostFormDto;
import apptive.team1.friendly.domain.post.dto.PostListDto;
import apptive.team1.friendly.domain.post.entity.AccountPost;
import apptive.team1.friendly.domain.post.entity.Post;
import apptive.team1.friendly.domain.post.repository.AccountPostRepository;
import apptive.team1.friendly.domain.post.repository.PostRepository;
import apptive.team1.friendly.domain.user.data.dto.AccountInfoResponse;
import apptive.team1.friendly.domain.user.data.entity.Account;
import apptive.team1.friendly.domain.user.data.repository.AccountRepository;
import apptive.team1.friendly.domain.user.service.UserService;
import apptive.team1.friendly.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final AccountRepository accountRepository;
    private final AccountPostRepository accountPostRepository;
    private final UserService userService;

    // 조회(READ)
    /**
     * 모든 게시물 찾아서 Post List DTO로 변환
     */
    public List<PostListDto> findAll() {
        List<Post> posts = postRepository.findAll();
        List<PostListDto> postListDtos = new ArrayList<PostListDto>();
        for (Post post : posts) {
            PostListDto postListDto = new PostListDto();

            postListDto.setPostId(post.getId());
            postListDto.setTitle(post.getTitle());
            postListDto.setMaxPeople(post.getMaxPeople());
            postListDto.setHashTag(post.getHashTag());
            postListDto.setPromiseTime(post.getPromiseTime());
//            postListDto.setImage(post.getImage());
            postListDto.setPromiseTime(post.getPromiseTime());

            postListDtos.add(postListDto);
        }
        return postListDtos;
    }

    /**
     * postId로 해당 게시물 찾기
     */
    public Post findByPostId(Long id) {
        return postRepository.findOneByPostId(id);
    }

    /**
     * userId로 user가 쓴 게시물 리스트 찾기
     */
    public List<Post> findPostsByUserId(Long userId) {
        return accountPostRepository.findByUser(userId);
    }


    // CUD
    /**
     * 게시물 추가하기
     */
    @Transactional
    public Long addPost(PostFormDto postFormDto) {
        // author 찾기
//        Account author = SecurityUtil.getCurrentUserName().flatMap(accountRepository::findOneWithAccountAuthoritiesByEmail).orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        // test용
        Account author = new Account();
        author.setEmail("test@test");
        author.setFirstName("mw");
        author.setLastName("mw2");
        //

        Post post = new Post(postFormDto.getTitle(),
                postFormDto.getDescription(),
                postFormDto.getMaxPeople(),
                postFormDto.getPromiseTime(),
                postFormDto.getLocation(),
                postFormDto.getRules(),
                postFormDto.getHashTag()
        );

        // AccountPost 생성하고, Account와 연관 관계 설정 해준다.
        AccountPost newAccountPost = new AccountPost();
        newAccountPost.setUser(author);

        // AccountPost를 Post와 연결
        newAccountPost.setPost(post);

        // post 저장, postId 리턴
        return accountPostRepository.save(newAccountPost);
    }

    /**
     * 게시물 삭제
     */
    @Transactional
    public Long deletePost(Long postId) {
        // author 찾기
//        Account author = SecurityUtil.getCurrentUserName().flatMap(accountRepository::findOneWithAccountAuthoritiesByEmail).orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
        // test용
//        Account author = new Account();
//        author.setEmail("test@test");
//        author.setFirstName("mw");
//        author.setLastName("mw2");

        //

        // 삭제한 post의 id 리턴. test용 9번 아이디 user
        return accountPostRepository.delete(7L, postId);

    }

    /**
     * 게시물 수정
     */
    @Transactional
    public Long updatePost(Long postId, PostFormDto postFormDto) {
        // author 찾기
//        Account author = SecurityUtil.getCurrentUserName().flatMap(accountRepository::findOneWithAccountAuthoritiesByEmail).orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        // test용
//        Account author = new Account();
//        author.setEmail("test@test");
//        author.setFirstName("mw");
//        author.setLastName("mw2");
        //

        // update한 post의 id 리턴
//        return accountPostRepository.update(postId, author.getId(), postFormDto);
        return accountPostRepository.update(9L, 8L, postFormDto);
    }

    public PostDto setPostDto(Long postId) {
        Post findPost = findByPostId(postId);

        // post id로 user 찾는 기능 추가
//        Account postOwner = userService.findByPostId(postId);

//        AccountInfoResponse accountInfo = userService.accountToUserInfo(postOwner);

        PostDto postDto = new PostDto();

        // accountInfo를 설정
//        postDto.setAccountInfo(accountInfo);

        postDto.setPostId(findPost.getId());
        postDto.setTitle(findPost.getTitle());
        postDto.setMaxPeople(findPost.getMaxPeople());
        postDto.setDescription(findPost.getDescription());
        postDto.setRules(findPost.getRules());
        postDto.setHashTag(findPost.getHashTag());
        postDto.setPromiseTime(findPost.getPromiseTime());

        return postDto;
    }

}
