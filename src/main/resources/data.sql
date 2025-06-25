-- === Member 10명 생성 (반드시 Post보다 먼저 실행되어야 합니다) ===
-- RoleType: ROLE_USER, ROLE_ADMIN
-- SocialType: KAKAO, GOOGLE
INSERT INTO members (id, role_type, social_type, nickname, created_at, updated_at) VALUES (1, 'ROLE_USER', 'KAKAO', '행복한 코알라', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, nickname, created_at, updated_at) VALUES (2, 'ROLE_USER', 'GOOGLE', '슬기로운 미어캣', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, nickname, created_at, updated_at) VALUES (3, 'ROLE_USER', 'KAKAO', '용감한 펭귄', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, nickname, created_at, updated_at) VALUES (4, 'ROLE_USER', 'GOOGLE', '신비로운 유니콘', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, nickname, created_at, updated_at) VALUES (5, 'ROLE_USER', 'KAKAO', '춤추는 알파카', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, nickname, created_at, updated_at) VALUES (6, 'ROLE_USER', 'GOOGLE', '고요한 나무늘보', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, nickname, created_at, updated_at) VALUES (7, 'ROLE_USER', 'KAKAO', '씩씩한 햄스터', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, nickname, created_at, updated_at) VALUES (8, 'ROLE_USER', 'GOOGLE', '자유로운 돌고래', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, nickname, created_at, updated_at) VALUES (9, 'ROLE_USER', 'KAKAO', '어쩌구 저쩌구', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, nickname, created_at, updated_at) VALUES (10, 'ROLE_USER', 'GOOGLE', '명상하는 여우', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, nickname, created_at, updated_at) VALUES (11, 'ROLE_ADMIN', 'KAKAO', '관리자쿼카', NOW(), NOW());

-- === Post 200개 생성 (회원별 개수 차등) ===
-- Post의 author_id는 위에서 생성한 Member의 id를 참조합니다.
-- Emotion: HAPPY, SAD, ANGRY, EXCITED, CONFUSED, PROUD
-- Concept: CLASSICAL_POETRY, POETRY, NOVEL, DRAMA, ESSAY

-- Member 1 (author_id=1): 10개
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (1, 1, '오늘 정말 행복한 하루였어요. 수정 전 내용.', '오늘 정말 행복한 하루였습니다. AI가 다듬은 내용.', '행복한 날', 'https://placehold.co/600x400?text=Post+1', false, false, false, 'HAPPY', 'ESSAY', NOW(), NOW());
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (2, 1, '길을 잃어서 슬펐어요.', '길을 잃어 잠시 슬픔에 잠겼습니다.', '슬픈 날', 'https://placehold.co/600x400?text=Post+2', false, false, false, 'SAD', 'POETRY', NOW(), NOW());
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (3, 1, '버스가 눈앞에서 지나가서 화가 났어요.', '버스가 눈앞에서 지나가 화가 치밀어 올랐습니다.', '화나는 날', 'https://placehold.co/600x400?text=Post+3', false, false, false, 'ANGRY', 'DRAMA', NOW(), NOW());
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (4, 1, '선물 받아서 신나요!', '생각지도 못한 선물에 마음이 들뜹니다!', '신나는 날', 'https://placehold.co/600x400?text=Post+4', false, false, false, 'EXCITED', 'NOVEL', NOW(), NOW());
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (5, 1, '이게 맞나? 혼란스러워요.', '무엇이 옳은 길인지 혼란스럽습니다.', '혼란스러운 날', 'https://placehold.co/600x400?text=Post+5', false, false, false, 'CONFUSED', 'CLASSICAL_POETRY', NOW(), NOW());
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (6, 1, '스스로가 자랑스러워요.', '오늘의 성취가 저를 자랑스럽게 만듭니다.', '자랑스러운 날', 'https://placehold.co/600x400?text=Post+6', false, false, false, 'PROUD', 'ESSAY', NOW(), NOW());
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (7, 1, '행복한 하루의 기록', '행복으로 가득했던 하루를 기록합니다.', '행복 기록', 'https://placehold.co/600x400?text=Post+7', false, false, false, 'HAPPY', 'POETRY', NOW(), NOW());
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (8, 1, '옛날 생각이 나서 슬퍼요.', '문득 떠오른 옛 생각에 마음이 아려옵니다.', '추억과 슬픔', 'https://placehold.co/600x400?text=Post+8', false, false, false, 'SAD', 'DRAMA', NOW(), NOW());
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (9, 1, '부당한 일에 화가 나요.', '세상의 부당함에 분노를 느낍니다.', '정의로운 분노', 'https://placehold.co/600x400?text=Post+9', false, false, false, 'ANGRY', 'NOVEL', NOW(), NOW());
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (10, 1, '내일 소풍가서 신나요.', '내일 있을 소풍 생각에 가슴이 뜁니다.', '소풍 전날', 'https://placehold.co/600x400?text=Post+10', false, false, false, 'EXCITED', 'CLASSICAL_POETRY', NOW(), NOW());

-- Member 2 (author_id=2): 12개
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) SELECT 10+n, 2, concat('내용 ', 10+n), concat('수정된 내용 ', 10+n), concat('제목 ', 10+n), concat('https://placehold.co/600x400?text=Post+', 10+n), false, false, false, ELT(MOD(n,6)+1, 'HAPPY','SAD','ANGRY','EXCITED','CONFUSED','PROUD'), ELT(MOD(n,5)+1, 'CLASSICAL_POETRY','POETRY','NOVEL','DRAMA','ESSAY'), NOW(), NOW() FROM (SELECT (n1.n + n10.n*10) as n FROM (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1, (SELECT 0 as n UNION ALL SELECT 1) n10) nums WHERE n BETWEEN 1 AND 12;

-- Member 3 (author_id=3): 14개
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) SELECT 22+n, 3, concat('내용 ', 22+n), concat('수정된 내용 ', 22+n), concat('제목 ', 22+n), concat('https://placehold.co/600x400?text=Post+', 22+n), false, false, false, ELT(MOD(n,6)+1, 'HAPPY','SAD','ANGRY','EXCITED','CONFUSED','PROUD'), ELT(MOD(n,5)+1, 'CLASSICAL_POETRY','POETRY','NOVEL','DRAMA','ESSAY'), NOW(), NOW() FROM (SELECT (n1.n + n10.n*10) as n FROM (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1, (SELECT 0 as n UNION ALL SELECT 1) n10) nums WHERE n BETWEEN 1 AND 14;

-- Member 4 (author_id=4): 20개
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) SELECT 36+n, 4, concat('내용 ', 36+n), concat('수정된 내용 ', 36+n), concat('제목 ', 36+n), concat('https://placehold.co/600x400?text=Post+', 36+n), false, false, false, ELT(MOD(n,6)+1, 'HAPPY','SAD','ANGRY','EXCITED','CONFUSED','PROUD'), ELT(MOD(n,5)+1, 'CLASSICAL_POETRY','POETRY','NOVEL','DRAMA','ESSAY'), NOW(), NOW() FROM (SELECT (n1.n + n10.n*10) as n FROM (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1, (SELECT 0 as n UNION ALL SELECT 1) n10) nums WHERE n BETWEEN 1 AND 20;

-- Member 5 (author_id=5): 22개
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) SELECT 56+n, 5, concat('내용 ', 56+n), concat('수정된 내용 ', 56+n), concat('제목 ', 56+n), concat('https://placehold.co/600x400?text=Post+', 56+n), false, false, false, ELT(MOD(n,6)+1, 'HAPPY','SAD','ANGRY','EXCITED','CONFUSED','PROUD'), ELT(MOD(n,5)+1, 'CLASSICAL_POETRY','POETRY','NOVEL','DRAMA','ESSAY'), NOW(), NOW() FROM (SELECT (n1.n + n10.n*10) as n FROM (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1, (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2) n10) nums WHERE n BETWEEN 1 AND 22;

-- Member 6 (author_id=6): 25개
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) SELECT 78+n, 6, concat('내용 ', 78+n), concat('수정된 내용 ', 78+n), concat('제목 ', 78+n), concat('https://placehold.co/600x400?text=Post+', 78+n), false, false, false, ELT(MOD(n,6)+1, 'HAPPY','SAD','ANGRY','EXCITED','CONFUSED','PROUD'), ELT(MOD(n,5)+1, 'CLASSICAL_POETRY','POETRY','NOVEL','DRAMA','ESSAY'), NOW(), NOW() FROM (SELECT (n1.n + n10.n*10) as n FROM (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1, (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2) n10) nums WHERE n BETWEEN 1 AND 25;

-- Member 7 (author_id=7): 30개
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) SELECT 103+n, 7, concat('내용 ', 103+n), concat('수정된 내용 ', 103+n), concat('제목 ', 103+n), concat('https://placehold.co/600x400?text=Post+', 103+n), false, false, false, ELT(MOD(n,6)+1, 'HAPPY','SAD','ANGRY','EXCITED','CONFUSED','PROUD'), ELT(MOD(n,5)+1, 'CLASSICAL_POETRY','POETRY','NOVEL','DRAMA','ESSAY'), NOW(), NOW() FROM (SELECT (n1.n + n10.n*10) as n FROM (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1, (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2) n10) nums WHERE n BETWEEN 1 AND 30;

-- Member 8 (author_id=8): 37개
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) SELECT 133+n, 8, concat('내용 ', 133+n), concat('수정된 내용 ', 133+n), concat('제목 ', 133+n), concat('https://placehold.co/600x400?text=Post+', 133+n), false, false, false, ELT(MOD(n,6)+1, 'HAPPY','SAD','ANGRY','EXCITED','CONFUSED','PROUD'), ELT(MOD(n,5)+1, 'CLASSICAL_POETRY','POETRY','NOVEL','DRAMA','ESSAY'), NOW(), NOW() FROM (SELECT (n1.n + n10.n*10) as n FROM (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1, (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3) n10) nums WHERE n BETWEEN 1 AND 37;

-- Member 9 (author_id=9): 15개
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) SELECT 170+n, 9, concat('내용 ', 170+n), concat('수정된 내용 ', 170+n), concat('제목 ', 170+n), concat('https://placehold.co/600x400?text=Post+', 170+n), false, false, false, ELT(MOD(n,6)+1, 'HAPPY','SAD','ANGRY','EXCITED','CONFUSED','PROUD'), ELT(MOD(n,5)+1, 'CLASSICAL_POETRY','POETRY','NOVEL','DRAMA','ESSAY'), NOW(), NOW() FROM (SELECT (n1.n + n10.n*10) as n FROM (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1, (SELECT 0 as n UNION ALL SELECT 1) n10) nums WHERE n BETWEEN 1 AND 15;

-- Member 10 (author_id=10): 15개
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) SELECT 185+n, 10, concat('내용 ', 185+n), concat('수정된 내용 ', 185+n), concat('제목 ', 185+n), concat('https://placehold.co/600x400?text=Post+', 185+n), false, false, false, ELT(MOD(n,6)+1, 'HAPPY','SAD','ANGRY','EXCITED','CONFUSED','PROUD'), ELT(MOD(n,5)+1, 'CLASSICAL_POETRY','POETRY','NOVEL','DRAMA','ESSAY'), NOW(), NOW() FROM (SELECT (n1.n + n10.n*10) as n FROM (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1, (SELECT 0 as n UNION ALL SELECT 1) n10) nums WHERE n BETWEEN 1 AND 15;


-- === 동적 날짜의 Post 200개 추가 생성 ===
-- 실행 시점 기준으로 어제와 그제 날짜의 데이터가 생성됩니다.
-- 이 데이터는 이전에 생성한 데이터와는 별개입니다. ID는 201부터 시작합니다.

-- 그제 날짜의 게시글 100개
-- DATE_SUB(CURDATE(), INTERVAL 2 DAY) : 실행일 기준 그제 날짜
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at)
SELECT
    200 + n, -- id
    FLOOR(1 + RAND() * 10), -- random author_id (1~10)
    CONCAT('그제 내용 ', 200 + n),
    CONCAT('수정된 그제 내용 ', 200 + n),
    CONCAT('그제 제목 ', 200 + n),
    CONCAT('https://placehold.co/600x400?text=Post+', 200 + n),
    false, false, false,
    ELT(MOD(n, 6) + 1, 'HAPPY', 'SAD', 'ANGRY', 'EXCITED', 'CONFUSED', 'PROUD'),
    ELT(MOD(n, 5) + 1, 'CLASSICAL_POETRY', 'POETRY', 'NOVEL', 'DRAMA', 'ESSAY'),
    TIMESTAMP(DATE_SUB(CURDATE(), INTERVAL 2 DAY)) + INTERVAL FLOOR(RAND() * 86400) SECOND,
    TIMESTAMP(DATE_SUB(CURDATE(), INTERVAL 2 DAY)) + INTERVAL FLOOR(RAND() * 86400) SECOND
FROM
    (SELECT (n1.n + n10.n*10) as n FROM
                                       (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1,
                                       (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n10
    ) nums
WHERE n BETWEEN 1 AND 100;


-- 어제 날짜의 게시글 100개
-- DATE_SUB(CURDATE(), INTERVAL 1 DAY) : 실행일 기준 어제 날짜
INSERT INTO posts(id, author_id, before_content, after_content, title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at)
SELECT
    300 + n, -- id
    FLOOR(1 + RAND() * 10), -- random author_id (1~10)
    CONCAT('어제 내용 ', 300 + n),
    CONCAT('수정된 어제 내용 ', 300 + n),
    CONCAT('어제 제목 ', 300 + n),
    CONCAT('https://placehold.co/600x400?text=Post+', 300 + n),
    false, false, false,
    ELT(MOD(n, 6) + 1, 'HAPPY', 'SAD', 'ANGRY', 'EXCITED', 'CONFUSED', 'PROUD'),
    ELT(MOD(n, 5) + 1, 'CLASSICAL_POETRY', 'POETRY', 'NOVEL', 'DRAMA', 'ESSAY'),
    TIMESTAMP(DATE_SUB(CURDATE(), INTERVAL 1 DAY)) + INTERVAL FLOOR(RAND() * 86400) SECOND,
    TIMESTAMP(DATE_SUB(CURDATE(), INTERVAL 1 DAY)) + INTERVAL FLOOR(RAND() * 86400) SECOND
FROM
    (SELECT (n1.n + n10.n*10) as n FROM
                                       (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1,
                                       (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n10
    ) nums
WHERE n BETWEEN 1 AND 100;

