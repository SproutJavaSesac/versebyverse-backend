-- === Member 10명 생성 (반드시 Post보다 먼저 실행되어야 합니다) ===
-- RoleType: ROLE_USER, ROLE_ADMIN
-- SocialType: KAKAO, GOOGLE
INSERT INTO members (id, role_type, social_type, email, nickname, is_deleted, created_at,
                     updated_at)
VALUES (1, 'ROLE_USER', 'KAKAO', 'sesac1@gmail.com', '행복한 코알라', false, NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, is_deleted, created_at,
                     updated_at)
VALUES (2, 'ROLE_USER', 'GOOGLE', 'sesac2@gmail.com', '슬기로운 미어캣', false, NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, is_deleted, created_at,
                     updated_at)
VALUES (3, 'ROLE_USER', 'KAKAO', 'sesac3@gmail.com', '용감한 펭귄', false, NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, is_deleted, created_at,
                     updated_at)
VALUES (4, 'ROLE_USER', 'GOOGLE', 'sesac4@gmail.com', '신비로운 유니콘', false, NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, is_deleted, created_at,
                     updated_at)
VALUES (5, 'ROLE_USER', 'KAKAO', 'sesac5@gmail.com', '춤추는 알파카', false, NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, is_deleted, created_at,
                     updated_at)
VALUES (6, 'ROLE_USER', 'GOOGLE', 'sesac6@gmail.com', '고요한 나무늘보', false, NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, is_deleted, created_at,
                     updated_at)
VALUES (7, 'ROLE_USER', 'KAKAO', 'sesac7@gmail.com', '씩씩한 햄스터', false, NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, is_deleted, created_at,
                     updated_at)
VALUES (8, 'ROLE_USER', 'GOOGLE', 'sesac8@gmail.com', '자유로운 돌고래', false, NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, is_deleted, created_at,
                     updated_at)
VALUES (9, 'ROLE_USER', 'KAKAO', 'sesac9@gmail.com', '어쩌구 저쩌구', false, NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, is_deleted, created_at,
                     updated_at)
VALUES (10, 'ROLE_USER', 'GOOGLE', 'sesac10@gmail.com', '명상하는 여우', false, NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, is_deleted, created_at,
                     updated_at)
VALUES (11, 'ROLE_ADMIN', 'KAKAO', 'sesac11@gmail.com', '관리자쿼카', false, NOW(), NOW());

-- === Post 200개 생성 (회원별 개수 차등) ===
-- Post의 author_id는 위에서 생성한 Member의 id를 참조합니다.
-- Emotion: HAPPY, SAD, ANGRY, EXCITED, CONFUSED, PROUD
-- Genre: MODERN_LITERATURE, CLASSICAL_LITERATURE, COMMENTARY, COLUMN, CONTRIBUTION, BOOK_REVIEW, HIPSTER_FEED, MIDNIGHT_RADIO, ESSAY

-- Member 1 (author_id=1): 10개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
VALUES (1, 1, '오늘 정말 행복한 하루였어요. 수정 전 내용.', '오늘 정말 행복한 하루였습니다. AI가 다듬은 내용.', '행복한 날', '행복했던 하루',
        'https://placehold.co/600x400?text=Post+1', false, false, false, 'HAPPY', 'ESSAY', NOW(),
        NOW());
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
VALUES (2, 1, '길을 잃어서 슬펐어요.', '길을 잃어 잠시 슬픔에 잠겼습니다.', '슬픈 날', '길 잃은 마음',
        'https://placehold.co/600x400?text=Post+2', false, false, false, 'SAD', 'MODERN_LITERATURE',
        NOW(),
        NOW());
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
VALUES (3, 1, '버스가 눈앞에서 지나가서 화가 났어요.', '버스가 눈앞에서 지나가 화가 치밀어 올랐습니다.', '화나는 날', '놓친 버스와 분노',
        'https://placehold.co/600x400?text=Post+3', false, false, false, 'ANGRY',
        'CLASSICAL_LITERATURE', NOW(),
        NOW());
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
VALUES (4, 1, '선물 받아서 신나요!', '생각지도 못한 선물에 마음이 들뜹니다!', '신나는 날', '예상치 못한 기쁨',
        'https://placehold.co/600x400?text=Post+4', false, false, false, 'EXCITED', 'COMMENTARY',
        NOW(),
        NOW());
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
VALUES (5, 1, '이게 맞나? 혼란스러워요.', '무엇이 옳은 길인지 혼란스럽습니다.', '혼란스러운 날', '갈래길에서의 고민',
        'https://placehold.co/600x400?text=Post+5', false, false, false, 'CONFUSED',
        'COLUMN', NOW(), NOW());
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
VALUES (6, 1, '스스로가 자랑스러워요.', '오늘의 성취가 저를 자랑스럽게 만듭니다.', '자랑스러운 날', '성취감의 기록',
        'https://placehold.co/600x400?text=Post+6', false, false, false, 'PROUD', 'ESSAY', NOW(),
        NOW());
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
VALUES (7, 1, '행복한 하루의 기록', '행복으로 가득했던 하루를 기록합니다.', '행복 기록', '따스한 하루의 추억',
        'https://placehold.co/600x400?text=Post+7', false, false, false, 'HAPPY',
        'MODERN_LITERATURE', NOW(),
        NOW());
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
VALUES (8, 1, '옛날 생각이 나서 슬퍼요.', '문득 떠오른 옛 생각에 마음이 아려옵니다.', '추억과 슬픔', '지나간 시간의 그리움',
        'https://placehold.co/600x400?text=Post+8', false, false, false, 'SAD',
        'CLASSICAL_LITERATURE', NOW(),
        NOW());
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
VALUES (9, 1, '부당한 일에 화가 나요.', '세상의 부당함에 분노를 느낍니다.', '정의로운 분노', '불의에 맞서는 마음',
        'https://placehold.co/600x400?text=Post+9', false, false, false, 'ANGRY', 'COMMENTARY',
        NOW(),
        NOW());
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
VALUES (10, 1, '내일 소풍가서 신나요.', '내일 있을 소풍 생각에 가슴이 뜁니다.', '소풍 전날', '설레는 마음의 기대',
        'https://placehold.co/600x400?text=Post+10', false, false, false, 'EXCITED',
        'COLUMN', NOW(), NOW());

-- Member 2 (author_id=2): 12개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
SELECT 10 + n,
       2,
       concat('내용 ', 10 + n),
       concat('수정된 내용 ', 10 + n),
       concat('제목 ', 10 + n),
       concat('다듬어진 제목 ', 10 + n),
       concat('https://placehold.co/600x400?text=Post+', 10 + n),
       false,
       false,
       false,
       ELT(MOD(n, 6) + 1, 'HAPPY', 'SAD', 'ANGRY', 'EXCITED', 'CONFUSED', 'PROUD'),
       ELT(MOD(n, 9) + 1, 'MODERN_LITERATURE', 'CLASSICAL_LITERATURE', 'COMMENTARY', 'COLUMN',
           'CONTRIBUTION', 'BOOK_REVIEW', 'HIPSTER_FEED', 'MIDNIGHT_RADIO', 'ESSAY'),
       NOW(),
       NOW()
FROM (SELECT (n1.n + n10.n * 10) as n
      FROM (SELECT 0 as n
            UNION ALL
            SELECT 1
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7
            UNION ALL
            SELECT 8
            UNION ALL
            SELECT 9) n1,
           (SELECT 0 as n UNION ALL SELECT 1) n10) nums
WHERE n BETWEEN 1 AND 12;

-- Member 3 (author_id=3): 14개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
SELECT 22 + n,
       3,
       concat('내용 ', 22 + n),
       concat('수정된 내용 ', 22 + n),
       concat('제목 ', 22 + n),
       concat('다듬어진 제목 ', 22 + n),
       concat('https://placehold.co/600x400?text=Post+', 22 + n),
       false,
       false,
       false,
       ELT(MOD(n, 6) + 1, 'HAPPY', 'SAD', 'ANGRY', 'EXCITED', 'CONFUSED', 'PROUD'),
       ELT(MOD(n, 9) + 1, 'MODERN_LITERATURE', 'CLASSICAL_LITERATURE', 'COMMENTARY', 'COLUMN',
           'CONTRIBUTION', 'BOOK_REVIEW', 'HIPSTER_FEED', 'MIDNIGHT_RADIO', 'ESSAY'),
       NOW(),
       NOW()
FROM (SELECT (n1.n + n10.n * 10) as n
      FROM (SELECT 0 as n
            UNION ALL
            SELECT 1
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7
            UNION ALL
            SELECT 8
            UNION ALL
            SELECT 9) n1,
           (SELECT 0 as n UNION ALL SELECT 1) n10) nums
WHERE n BETWEEN 1 AND 14;

-- Member 4 (author_id=4): 20개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
SELECT 36 + n,
       4,
       concat('내용 ', 36 + n),
       concat('수정된 내용 ', 36 + n),
       concat('제목 ', 36 + n),
       concat('다듬어진 제목 ', 36 + n),
       concat('https://placehold.co/600x400?text=Post+', 36 + n),
       false,
       false,
       false,
       ELT(MOD(n, 6) + 1, 'HAPPY', 'SAD', 'ANGRY', 'EXCITED', 'CONFUSED', 'PROUD'),
       ELT(MOD(n, 9) + 1, 'MODERN_LITERATURE', 'CLASSICAL_LITERATURE', 'COMMENTARY', 'COLUMN',
           'CONTRIBUTION', 'BOOK_REVIEW', 'HIPSTER_FEED', 'MIDNIGHT_RADIO', 'ESSAY'),
       NOW(),
       NOW()
FROM (SELECT (n1.n + n10.n * 10) as n
      FROM (SELECT 0 as n
            UNION ALL
            SELECT 1
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7
            UNION ALL
            SELECT 8
            UNION ALL
            SELECT 9) n1,
           (SELECT 0 as n UNION ALL SELECT 1) n10) nums
WHERE n BETWEEN 1 AND 20;

-- Member 5 (author_id=5): 22개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
SELECT 56 + n,
       5,
       concat('내용 ', 56 + n),
       concat('수정된 내용 ', 56 + n),
       concat('제목 ', 56 + n),
       concat('다듬어진 제목 ', 56 + n),
       concat('https://placehold.co/600x400?text=Post+', 56 + n),
       false,
       false,
       false,
       ELT(MOD(n, 6) + 1, 'HAPPY', 'SAD', 'ANGRY', 'EXCITED', 'CONFUSED', 'PROUD'),
       ELT(MOD(n, 9) + 1, 'MODERN_LITERATURE', 'CLASSICAL_LITERATURE', 'COMMENTARY', 'COLUMN',
           'CONTRIBUTION', 'BOOK_REVIEW', 'HIPSTER_FEED', 'MIDNIGHT_RADIO', 'ESSAY'),
       NOW(),
       NOW()
FROM (SELECT (n1.n + n10.n * 10) as n
      FROM (SELECT 0 as n
            UNION ALL
            SELECT 1
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7
            UNION ALL
            SELECT 8
            UNION ALL
            SELECT 9) n1,
           (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2) n10) nums
WHERE n BETWEEN 1 AND 22;

-- Member 6 (author_id=6): 25개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
SELECT 78 + n,
       6,
       concat('내용 ', 78 + n),
       concat('수정된 내용 ', 78 + n),
       concat('제목 ', 78 + n),
       concat('다듬어진 제목 ', 78 + n),
       concat('https://placehold.co/600x400?text=Post+', 78 + n),
       false,
       false,
       false,
       ELT(MOD(n, 6) + 1, 'HAPPY', 'SAD', 'ANGRY', 'EXCITED', 'CONFUSED', 'PROUD'),
       ELT(MOD(n, 9) + 1, 'MODERN_LITERATURE', 'CLASSICAL_LITERATURE', 'COMMENTARY', 'COLUMN',
           'CONTRIBUTION', 'BOOK_REVIEW', 'HIPSTER_FEED', 'MIDNIGHT_RADIO', 'ESSAY'),
       NOW(),
       NOW()
FROM (SELECT (n1.n + n10.n * 10) as n
      FROM (SELECT 0 as n
            UNION ALL
            SELECT 1
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7
            UNION ALL
            SELECT 8
            UNION ALL
            SELECT 9) n1,
           (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2) n10) nums
WHERE n BETWEEN 1 AND 25;

-- Member 7 (author_id=7): 30개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
SELECT 103 + n,
       7,
       concat('내용 ', 103 + n),
       concat('수정된 내용 ', 103 + n),
       concat('제목 ', 103 + n),
       concat('다듬어진 제목 ', 103 + n),
       concat('https://placehold.co/600x400?text=Post+', 103 + n),
       false,
       false,
       false,
       ELT(MOD(n, 6) + 1, 'HAPPY', 'SAD', 'ANGRY', 'EXCITED', 'CONFUSED', 'PROUD'),
       ELT(MOD(n, 9) + 1, 'MODERN_LITERATURE', 'CLASSICAL_LITERATURE', 'COMMENTARY', 'COLUMN',
           'CONTRIBUTION', 'BOOK_REVIEW', 'HIPSTER_FEED', 'MIDNIGHT_RADIO', 'ESSAY'),
       NOW(),
       NOW()
FROM (SELECT (n1.n + n10.n * 10) as n
      FROM (SELECT 0 as n
            UNION ALL
            SELECT 1
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7
            UNION ALL
            SELECT 8
            UNION ALL
            SELECT 9) n1,
           (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2) n10) nums
WHERE n BETWEEN 1 AND 30;

-- Member 8 (author_id=8): 37개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
SELECT 133 + n,
       8,
       concat('내용 ', 133 + n),
       concat('수정된 내용 ', 133 + n),
       concat('제목 ', 133 + n),
       concat('다듬어진 제목 ', 133 + n),
       concat('https://placehold.co/600x400?text=Post+', 133 + n),
       false,
       false,
       false,
       ELT(MOD(n, 6) + 1, 'HAPPY', 'SAD', 'ANGRY', 'EXCITED', 'CONFUSED', 'PROUD'),
       ELT(MOD(n, 9) + 1, 'MODERN_LITERATURE', 'CLASSICAL_LITERATURE', 'COMMENTARY', 'COLUMN',
           'CONTRIBUTION', 'BOOK_REVIEW', 'HIPSTER_FEED', 'MIDNIGHT_RADIO', 'ESSAY'),
       NOW(),
       NOW()
FROM (SELECT (n1.n + n10.n * 10) as n
      FROM (SELECT 0 as n
            UNION ALL
            SELECT 1
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7
            UNION ALL
            SELECT 8
            UNION ALL
            SELECT 9) n1,
           (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3) n10) nums
WHERE n BETWEEN 1 AND 37;

-- Member 9 (author_id=9): 15개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
SELECT 170 + n,
       9,
       concat('내용 ', 170 + n),
       concat('수정된 내용 ', 170 + n),
       concat('제목 ', 170 + n),
       concat('다듬어진 제목 ', 170 + n),
       concat('https://placehold.co/600x400?text=Post+', 170 + n),
       false,
       false,
       false,
       ELT(MOD(n, 6) + 1, 'HAPPY', 'SAD', 'ANGRY', 'EXCITED', 'CONFUSED', 'PROUD'),
       ELT(MOD(n, 9) + 1, 'MODERN_LITERATURE', 'CLASSICAL_LITERATURE', 'COMMENTARY', 'COLUMN',
           'CONTRIBUTION', 'BOOK_REVIEW', 'HIPSTER_FEED', 'MIDNIGHT_RADIO', 'ESSAY'),
       NOW(),
       NOW()
FROM (SELECT (n1.n + n10.n * 10) as n
      FROM (SELECT 0 as n
            UNION ALL
            SELECT 1
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7
            UNION ALL
            SELECT 8
            UNION ALL
            SELECT 9) n1,
           (SELECT 0 as n UNION ALL SELECT 1) n10) nums
WHERE n BETWEEN 1 AND 15;

-- Member 10 (author_id=10): 15개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
SELECT 185 + n,
       10,
       concat('내용 ', 185 + n),
       concat('수정된 내용 ', 185 + n),
       concat('제목 ', 185 + n),
       concat('다듬어진 제목 ', 185 + n),
       concat('https://placehold.co/600x400?text=Post+', 185 + n),
       false,
       false,
       false,
       ELT(MOD(n, 6) + 1, 'HAPPY', 'SAD', 'ANGRY', 'EXCITED', 'CONFUSED', 'PROUD'),
       ELT(MOD(n, 9) + 1, 'MODERN_LITERATURE', 'CLASSICAL_LITERATURE', 'COMMENTARY', 'COLUMN',
           'CONTRIBUTION', 'BOOK_REVIEW', 'HIPSTER_FEED', 'MIDNIGHT_RADIO', 'ESSAY'),
       NOW(),
       NOW()
FROM (SELECT (n1.n + n10.n * 10) as n
      FROM (SELECT 0 as n
            UNION ALL
            SELECT 1
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7
            UNION ALL
            SELECT 8
            UNION ALL
            SELECT 9) n1,
           (SELECT 0 as n UNION ALL SELECT 1) n10) nums
WHERE n BETWEEN 1 AND 15;


-- === 동적 날짜의 Post 200개 추가 생성 ===
-- 실행 시점 기준으로 어제와 그제 날짜의 데이터가 생성됩니다.
-- 이 데이터는 이전에 생성한 데이터와는 별개입니다. ID는 201부터 시작합니다.

-- 그제 날짜의 게시글 100개
-- DATE_SUB(CURDATE(), INTERVAL 2 DAY) : 실행일 기준 그제 날짜
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
SELECT 200 + n,                -- id
       FLOOR(1 + RAND() * 10), -- random author_id (1~10)
       CONCAT('그제 내용 ', 200 + n),
       CONCAT('수정된 그제 내용 ', 200 + n),
       CONCAT('그제 제목 ', 200 + n),
       CONCAT('다듬어진 그제 제목 ', 200 + n),
       CONCAT('https://placehold.co/600x400?text=Post+', 200 + n),
       false,
       false,
       false,
       ELT(MOD(n, 6) + 1, 'HAPPY', 'SAD', 'ANGRY', 'EXCITED', 'CONFUSED', 'PROUD'),
       ELT(MOD(n, 9) + 1, 'MODERN_LITERATURE', 'CLASSICAL_LITERATURE', 'COMMENTARY', 'COLUMN',
           'CONTRIBUTION', 'BOOK_REVIEW', 'HIPSTER_FEED', 'MIDNIGHT_RADIO',
           'ESSAY'), TIMESTAMP (DATE_SUB(CURDATE(), INTERVAL 2 DAY)) + INTERVAL FLOOR(RAND() * 86400) SECOND, TIMESTAMP (DATE_SUB(CURDATE(), INTERVAL 2 DAY)) + INTERVAL FLOOR(RAND() * 86400) SECOND
FROM (SELECT (n1.n + n10.n * 10) as n
    FROM (SELECT 0 as n
    UNION ALL
    SELECT 1
    UNION ALL
    SELECT 2
    UNION ALL
    SELECT 3
    UNION ALL
    SELECT 4
    UNION ALL
    SELECT 5
    UNION ALL
    SELECT 6
    UNION ALL
    SELECT 7
    UNION ALL
    SELECT 8
    UNION ALL
    SELECT 9) n1, (SELECT 0 as n
    UNION ALL
    SELECT 1
    UNION ALL
    SELECT 2
    UNION ALL
    SELECT 3
    UNION ALL
    SELECT 4
    UNION ALL
    SELECT 5
    UNION ALL
    SELECT 6
    UNION ALL
    SELECT 7
    UNION ALL
    SELECT 8
    UNION ALL
    SELECT 9) n10) nums
WHERE n BETWEEN 1 AND 100;


-- 어제 날짜의 게시글 100개
-- DATE_SUB(CURDATE(), INTERVAL 1 DAY) : 실행일 기준 어제 날짜
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title,
                  image_url, is_blocked, is_deleted, is_hidden, emotion_type, genre_type,
                  created_at, updated_at)
SELECT 300 + n,                -- id
       FLOOR(1 + RAND() * 10), -- random author_id (1~10)
       CONCAT('어제 내용 ', 300 + n),
       CONCAT('수정된 어제 내용 ', 300 + n),
       CONCAT('어제 제목 ', 300 + n),
       CONCAT('다듬어진 어제 제목 ', 300 + n),
       CONCAT('https://placehold.co/600x400?text=Post+', 300 + n),
       false,
       false,
       false,
       ELT(MOD(n, 6) + 1, 'HAPPY', 'SAD', 'ANGRY', 'EXCITED', 'CONFUSED', 'PROUD'),
       ELT(MOD(n, 9) + 1, 'MODERN_LITERATURE', 'CLASSICAL_LITERATURE', 'COMMENTARY', 'COLUMN',
           'CONTRIBUTION', 'BOOK_REVIEW', 'HIPSTER_FEED', 'MIDNIGHT_RADIO',
           'ESSAY'), TIMESTAMP (DATE_SUB(CURDATE(), INTERVAL 1 DAY)) + INTERVAL FLOOR(RAND() * 86400) SECOND, TIMESTAMP (DATE_SUB(CURDATE(), INTERVAL 1 DAY)) + INTERVAL FLOOR(RAND() * 86400) SECOND
FROM (SELECT (n1.n + n10.n * 10) as n
    FROM (SELECT 0 as n
    UNION ALL
    SELECT 1
    UNION ALL
    SELECT 2
    UNION ALL
    SELECT 3
    UNION ALL
    SELECT 4
    UNION ALL
    SELECT 5
    UNION ALL
    SELECT 6
    UNION ALL
    SELECT 7
    UNION ALL
    SELECT 8
    UNION ALL
    SELECT 9) n1, (SELECT 0 as n
    UNION ALL
    SELECT 1
    UNION ALL
    SELECT 2
    UNION ALL
    SELECT 3
    UNION ALL
    SELECT 4
    UNION ALL
    SELECT 5
    UNION ALL
    SELECT 6
    UNION ALL
    SELECT 7
    UNION ALL
    SELECT 8
    UNION ALL
    SELECT 9) n10) nums
WHERE n BETWEEN 1 AND 100;


-- === 계층형 댓글 더미 데이터 200개 생성 (path, is_deleted 필드 포함) ===
-- post_id: 1~10에 할당, commenter_id: 1~10 사이에서 랜덤 할당
-- level: 0(최상위), 1(대댓글), 2(대대댓글)
-- path: 부모댓글경로-자식ID 형식으로 구성
-- is_deleted: 일부 댓글을 true로 설정하여 soft-delete 상태를 시뮬레이션합니다.

-- Post 1 (총 15개 댓글)
-- Level 0 (최상위 댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     path, is_deleted, is_blocked, created_at, updated_at)
VALUES (1, 1, 2, NULL, '정말 좋은 글이네요. 원본 댓글입니다.', '정말 좋은 글이네요. AI가 다듬은 댓글입니다.', 0, '1', false, false,
        NOW(), NOW()),
       (2, 1, 3, NULL, '많이 배우고 갑니다.', '많은 것을 배우고 갑니다.', 0, '2', false, false, NOW(), NOW()),
       (3, 1, 4, NULL, '혹시 이 부분에 대해 더 설명해주실 수 있나요?', '혹시 이 부분에 대해 조금 더 자세히 설명해주실 수 있을까요?', 0, '3',
        false, false, NOW(), NOW()),
       (4, 1, 5, NULL, '[삭제된 댓글] 저는 좀 다르게 생각해요.', '[삭제된 댓글] 저는 조금 다른 관점에서 생각합니다.', 0, '4', true,
        false, NOW(), NOW()),
       (5, 1, 6, NULL, '완전 공감!', '완벽하게 공감합니다!', 0, '5', false, false, NOW(), NOW()),
       (6, 1, 7, NULL, '이런 글을 써주셔서 감사합니다.', '이런 깊이 있는 글을 작성해주셔서 감사합니다.', 0, '6', false, false,
        NOW(), NOW());
-- Level 1 (대댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     path, is_deleted, is_blocked, created_at, updated_at)
VALUES (7, 1, 8, 1, '맞아요! 저도 그렇게 생각했어요.', '동의합니다! 저도 같은 생각을 했습니다.', 1, '1-7', false, false, NOW(),
        NOW()),
       (8, 1, 9, 1, '[삭제된 댓글] 글쓴이의 통찰력이 돋보이네요.', '[삭제된 댓글] 작성자님의 통찰력이 돋보이는 글입니다.', 1, '1-8', true,
        false, NOW(), NOW()),
       (9, 1, 10, 2, '배우셨다니 다행입니다.', '도움이 되셨다니 다행입니다.', 1, '2-9', false, false, NOW(), NOW()),
       (10, 1, 1, 3, '어떤 부분이 궁금하신가요?', '어떤 부분이 가장 궁금하신가요?', 1, '3-10', false, false, NOW(), NOW()),
       (11, 1, 2, 4, '오, 다른 생각도 궁금하네요!', '오, 다른 의견도 들어보고 싶습니다!', 1, '4-11', false, true, NOW(),
        NOW()),
       (12, 1, 3, 5, '공감해주셔서 저도 기분이 좋네요.', '공감해주시니 저도 기쁩니다.', 1, '5-12', false, false, NOW(),
        NOW());
-- Level 2 (대대댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     path, is_deleted, is_blocked, created_at, updated_at)
VALUES (13, 1, 4, 7, '두 분 다 대단하세요.', '두 분 모두 정말 대단하십니다.', 2, '1-7-13', false, false, NOW(), NOW()),
       (14, 1, 5, 10, '3번째 문단이요!', '세 번째 문단이 특히 궁금합니다!', 2, '3-10-14', false, false, NOW(), NOW()),
       (15, 1, 6, 11, '[삭제된 댓글] 제 생각은 이렇습니다...', '[삭제된 댓글] 제 생각은 이러합니다...', 2, '4-11-15', true,
        false, NOW(), NOW());

-- Post 2 (총 25개 댓글)
-- Level 0
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     path, is_deleted, is_blocked, created_at, updated_at)
VALUES (16, 2, 1, NULL, '포스트 2의 첫 댓글', '포스트 2의 첫 번째 댓글입니다.', 0, '16', false, false, NOW(), NOW()),
       (17, 2, 2, NULL, '흥미로운 주제네요.', '매우 흥미로운 주제입니다.', 0, '17', false, false, NOW(), NOW()),
       (18, 2, 3, NULL, '잘 읽었습니다.', '잘 읽고 갑니다.', 0, '18', false, false, NOW(), NOW()),
       (19, 2, 4, NULL, '[삭제된 댓글] 이런 관점도 있군요.', '[삭제된 댓글] 이러한 관점도 존재하군요.', 0, '19', true, false,
        NOW(), NOW()),
       (20, 2, 5, NULL, '다음 글도 기대됩니다.', '다음 글도 기대하겠습니다.', 0, '20', false, false, NOW(), NOW()),
       (21, 2, 6, NULL, '생각할 거리를 주네요.', '생각할 거리를 던져주는 글입니다.', 0, '21', false, false, NOW(), NOW()),
       (22, 2, 7, NULL, '스크랩해 갑니다.', '좋은 글이라 스크랩합니다.', 0, '22', false, false, NOW(), NOW()),
       (23, 2, 8, NULL, '최고예요!', '정말 최고입니다!', 0, '23', false, false, NOW(), NOW()),
       (24, 2, 9, NULL, '시간 가는 줄 모르고 읽었네요.', '시간 가는 줄 모르고 정독했습니다.', 0, '24', false, false, NOW(),
        NOW()),
       (25, 2, 10, NULL, '정리가 잘 되어있네요.', '내용이 일목요연하게 정리되어 있네요.', 0, '25', false, false, NOW(),
        NOW());
-- Level 1
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     path, is_deleted, is_blocked, created_at, updated_at)
VALUES (26, 2, 1, 16, '반갑습니다.', '만나서 반갑습니다.', 1, '16-26', false, false, NOW(), NOW()),
       (27, 2, 2, 17, '맞아요. 저도 관심 많아요.', '동의합니다. 저도 관심이 많은 분야입니다.', 1, '17-27', false, false, NOW(),
        NOW()),
       (28, 2, 3, 17, '관련해서 추천할 만한 책 있나요?', '이와 관련하여 추천할 만한 도서가 있을까요?', 1, '17-28', false, false,
        NOW(), NOW()),
       (29, 2, 4, 19, '새로운 시각이네요.', '참신한 시각입니다.', 1, '19-29', false, false, NOW(), NOW()),
       (30, 2, 5, 20, '저도요!', '저도 기대하고 있습니다!', 1, '20-30', false, false, NOW(), NOW()),
       (31, 2, 6, 21, '[삭제된 댓글] 고민이 깊어지는 밤입니다.', '[삭제된 댓글] 고민이 깊어지는 밤이네요.', 1, '21-31', true, false,
        NOW(), NOW()),
       (32, 2, 7, 22, '스크랩 감사합니다.', '스크랩해주셔서 감사합니다.', 1, '22-32', false, false, NOW(), NOW()),
       (33, 2, 8, 23, '최고라니요! 감사합니다.', '최고의 칭찬 감사합니다.', 1, '23-33', false, false, NOW(), NOW()),
       (34, 2, 9, 24, '재미있게 읽으셨다니 다행입니다.', '재미있게 읽으셨다니 다행이네요.', 1, '24-34', false, false, NOW(),
        NOW()),
       (35, 2, 10, 25, '감사합니다. 노력했어요.', '감사합니다. 노력의 산물입니다.', 1, '25-35', false, false, NOW(),
        NOW());
-- Level 2
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     path, is_deleted, is_blocked, created_at, updated_at)
VALUES (36, 2, 1, 27, '오, 어떤 점이 관심 가세요?', '오, 어떤 점이 가장 흥미로우신가요?', 2, '17-27-36', false, false,
        NOW(), NOW()),
       (37, 2, 2, 28, '저는 "생각에 관한 생각" 추천해요.', '저는 "생각에 관한 생각"이라는 책을 추천합니다.', 2, '17-28-37', false,
        false, NOW(), NOW()),
       (38, 2, 3, 30, '[삭제된 댓글] 작가님 팬이에요!', '[삭제된 댓글] 작가님의 오랜 팬입니다!', 2, '20-30-38', true, false,
        NOW(), NOW()),
       (39, 2, 4, 33, '아니에요. 정말 최고예요.', '아닙니다. 정말 최고의 글입니다.', 2, '23-33-39', false, false, NOW(),
        NOW()),
       (40, 2, 5, 35, '노력이 느껴져요.', '그 노력이 느껴지는 글입니다.', 2, '25-35-40', false, false, NOW(), NOW());

-- Post 3 (총 18개 댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     path, is_deleted, is_blocked, created_at, updated_at)
VALUES (41, 3, 6, NULL, 'P3 댓글 1', 'P3 댓글 1', 0, '41', false, false, NOW(), NOW()),
       (42, 3, 7, NULL, 'P3 댓글 2', 'P3 댓글 2', 0, '42', true, false, NOW(), NOW()),
       (43, 3, 8, NULL, 'P3 댓글 3', 'P3 댓글 3', 0, '43', false, false, NOW(), NOW()),
       (44, 3, 9, NULL, 'P3 댓글 4', 'P3 댓글 4', 0, '44', false, false, NOW(), NOW()),
       (45, 3, 10, NULL, 'P3 댓글 5', 'P3 댓글 5', 0, '45', false, false, NOW(), NOW()),
       (46, 3, 1, NULL, 'P3 댓글 6', 'P3 댓글 6', 0, '46', false, false, NOW(), NOW()),
       (47, 3, 2, NULL, 'P3 댓글 7', 'P3 댓글 7', 0, '47', false, false, NOW(), NOW()),
       (48, 3, 3, 41, 'P3 답글 1-1', 'P3 답글 1-1', 1, '41-48', false, false, NOW(), NOW()),
       (49, 3, 4, 42, 'P3 답글 2-1', 'P3 답글 2-1', 1, '42-49', false, false, NOW(), NOW()),
       (50, 3, 5, 43, 'P3 답글 3-1', 'P3 답글 3-1', 1, '43-50', true, false, NOW(), NOW()),
       (51, 3, 6, 43, 'P3 답글 3-2', 'P3 답글 3-2', 1, '43-51', false, false, NOW(), NOW()),
       (52, 3, 7, 45, 'P3 답글 5-1', 'P3 답글 5-1', 1, '45-52', false, false, NOW(), NOW()),
       (53, 3, 8, 46, 'P3 답글 6-1', 'P3 답글 6-1', 1, '46-53', false, false, NOW(), NOW()),
       (54, 3, 9, 47, 'P3 답글 7-1', 'P3 답글 7-1', 1, '47-54', false, false, NOW(), NOW()),
       (55, 3, 10, 50, 'P3 대답글 3-1-1', 'P3 대답글 3-1-1', 2, '43-50-55', false, false, NOW(), NOW()),
       (56, 3, 1, 51, 'P3 대답글 3-2-1', 'P3 대답글 3-2-1', 2, '43-51-56', false, false, NOW(), NOW()),
       (57, 3, 2, 52, 'P3 대답글 5-1-1', 'P3 대답글 5-1-1', 2, '45-52-57', true, false, NOW(), NOW()),
       (58, 3, 3, 54, 'P3 대답글 7-1-1', 'P3 대답글 7-1-1', 2, '47-54-58', false, false, NOW(), NOW());

-- Post 4 (총 22개 댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     path, is_deleted, is_blocked, created_at, updated_at)
VALUES (59, 4, 1, NULL, 'P4 댓글 1', 'P4 댓글 1', 0, '59', false, false, NOW(), NOW()),
       (60, 4, 2, NULL, 'P4 댓글 2', 'P4 댓글 2', 0, '60', false, false, NOW(), NOW()),
       (61, 4, 3, NULL, 'P4 댓글 3', 'P4 댓글 3', 0, '61', false, false, NOW(), NOW()),
       (62, 4, 4, NULL, 'P4 댓글 4', 'P4 댓글 4', 0, '62', false, false, NOW(), NOW()),
       (63, 4, 5, NULL, 'P4 댓글 5', 'P4 댓글 5', 0, '63', false, false, NOW(), NOW()),
       (64, 4, 6, NULL, 'P4 댓글 6', 'P4 댓글 6', 0, '64', false, false, NOW(), NOW()),
       (65, 4, 7, NULL, 'P4 댓글 7', 'P4 댓글 7', 0, '65', false, false, NOW(), NOW()),
       (66, 4, 8, NULL, 'P4 댓글 8', 'P4 댓글 8', 0, '66', true, false, NOW(), NOW()),
       (67, 4, 9, 59, 'P4 답글 1', 'P4 답글 1', 1, '59-67', false, false, NOW(), NOW()),
       (68, 4, 10, 59, 'P4 답글 2', 'P4 답글 2', 1, '59-68', false, false, NOW(), NOW()),
       (69, 4, 1, 60, 'P4 답글 3', 'P4 답글 3', 1, '60-69', false, false, NOW(), NOW()),
       (70, 4, 2, 61, 'P4 답글 4', 'P4 답글 4', 1, '61-70', true, false, NOW(), NOW()),
       (71, 4, 3, 62, 'P4 답글 5', 'P4 답글 5', 1, '62-71', false, false, NOW(), NOW()),
       (72, 4, 4, 63, 'P4 답글 6', 'P4 답글 6', 1, '63-72', false, false, NOW(), NOW()),
       (73, 4, 5, 64, 'P4 답글 7', 'P4 답글 7', 1, '64-73', false, false, NOW(), NOW()),
       (74, 4, 6, 65, 'P4 답글 8', 'P4 답글 8', 1, '65-74', false, false, NOW(), NOW()),
       (75, 4, 7, 66, 'P4 답글 9', 'P4 답글 9', 1, '66-75', false, false, NOW(), NOW()),
       (76, 4, 8, 67, 'P4 대답글 1', 'P4 대답글 1', 2, '59-67-76', false, false, NOW(), NOW()),
       (77, 4, 9, 68, 'P4 대답글 2', 'P4 대답글 2', 2, '59-68-77', false, false, NOW(), NOW()),
       (78, 4, 10, 70, 'P4 대답글 3', 'P4 대답글 3', 2, '61-70-78', false, false, NOW(), NOW()),
       (79, 4, 1, 72, 'P4 대답글 4', 'P4 대답글 4', 2, '63-72-79', true, false, NOW(), NOW()),
       (80, 4, 2, 75, 'P4 대답글 5', 'P4 대답글 5', 2, '66-75-80', false, false, NOW(), NOW());

-- Post 5 (총 10개 댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     path, is_deleted, is_blocked, created_at, updated_at)
VALUES (81, 5, 3, NULL, 'P5 댓글 1', 'P5 댓글 1', 0, '81', false, false, NOW(), NOW()),
       (82, 5, 4, NULL, 'P5 댓글 2', 'P5 댓글 2', 0, '82', false, false, NOW(), NOW()),
       (83, 5, 5, NULL, 'P5 댓글 3', 'P5 댓글 3', 0, '83', false, false, NOW(), NOW()),
       (84, 5, 6, NULL, 'P5 댓글 4', 'P5 댓글 4', 0, '84', false, false, NOW(), NOW()),
       (85, 5, 7, 81, 'P5 답글 1', 'P5 답글 1', 1, '81-85', false, false, NOW(), NOW()),
       (86, 5, 8, 81, 'P5 답글 2', 'P5 답글 2', 1, '81-86', false, false, NOW(), NOW()),
       (87, 5, 9, 82, 'P5 답글 3', 'P5 답글 3', 1, '82-87', true, false, NOW(), NOW()),
       (88, 5, 10, 84, 'P5 답글 4', 'P5 답글 4', 1, '84-88', false, false, NOW(), NOW()),
       (89, 5, 1, 85, 'P5 대답글 1', 'P5 대답글 1', 2, '81-85-89', false, false, NOW(), NOW()),
       (90, 5, 2, 88, 'P5 대답글 2', 'P5 대답글 2', 2, '84-88-90', false, false, NOW(), NOW());

-- Post 6 (총 30개 댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     path, is_deleted, is_blocked, created_at, updated_at)
VALUES (91, 6, 1, NULL, 'P6-L0-1', 'P6-L0-1', 0, '91', false, false, NOW(), NOW()),
       (92, 6, 2, NULL, 'P6-L0-2', 'P6-L0-2', 0, '92', false, false, NOW(), NOW()),
       (93, 6, 3, NULL, 'P6-L0-3', 'P6-L0-3', 0, '93', false, false, NOW(), NOW()),
       (94, 6, 4, NULL, 'P6-L0-4', 'P6-L0-4', 0, '94', false, false, NOW(), NOW()),
       (95, 6, 5, NULL, 'P6-L0-5', 'P6-L0-5', 0, '95', false, false, NOW(), NOW()),
       (96, 6, 6, NULL, 'P6-L0-6', 'P6-L0-6', 0, '96', false, false, NOW(), NOW()),
       (97, 6, 7, NULL, 'P6-L0-7', 'P6-L0-7', 0, '97', false, false, NOW(), NOW()),
       (98, 6, 8, NULL, 'P6-L0-8', 'P6-L0-8', 0, '98', false, false, NOW(), NOW()),
       (99, 6, 9, NULL, 'P6-L0-9', 'P6-L0-9', 0, '99', false, false, NOW(), NOW()),
       (100, 6, 10, NULL, 'P6-L0-10', 'P6-L0-10', 0, '100', false, false, NOW(), NOW()),
       (101, 6, 1, 91, 'P6-L1-1', 'P6-L1-1', 1, '91-101', false, false, NOW(), NOW()),
       (102, 6, 2, 91, 'P6-L1-2', 'P6-L1-2', 1, '91-102', false, false, NOW(), NOW()),
       (103, 6, 3, 92, 'P6-L1-3', 'P6-L1-3', 1, '92-103', false, false, NOW(), NOW()),
       (104, 6, 4, 93, 'P6-L1-4', 'P6-L1-4', 1, '93-104', false, false, NOW(), NOW()),
       (105, 6, 5, 94, 'P6-L1-5', 'P6-L1-5', 1, '94-105', false, false, NOW(), NOW()),
       (106, 6, 6, 95, 'P6-L1-6', 'P6-L1-6', 1, '95-106', false, false, NOW(), NOW()),
       (107, 6, 7, 96, 'P6-L1-7', 'P6-L1-7', 1, '96-107', false, false, NOW(), NOW()),
       (108, 6, 8, 97, 'P6-L1-8', 'P6-L1-8', 1, '97-108', false, false, NOW(), NOW()),
       (109, 6, 9, 98, 'P6-L1-9', 'P6-L1-9', 1, '98-109', false, false, NOW(), NOW()),
       (110, 6, 10, 100, 'P6-L1-10', 'P6-L1-10', 1, '100-110', false, false, NOW(), NOW()),
       (111, 6, 1, 101, 'P6-L2-1', 'P6-L2-1', 2, '91-101-111', false, false, NOW(), NOW()),
       (112, 6, 2, 101, 'P6-L2-2', 'P6-L2-2', 2, '91-101-112', false, false, NOW(), NOW()),
       (113, 6, 3, 102, 'P6-L2-3', 'P6-L2-3', 2, '91-102-113', false, false, NOW(), NOW()),
       (114, 6, 4, 103, 'P6-L2-4', 'P6-L2-4', 2, '92-103-114', false, false, NOW(), NOW()),
       (115, 6, 5, 104, 'P6-L2-5', 'P6-L2-5', 2, '93-104-115', false, false, NOW(), NOW()),
       (116, 6, 6, 105, 'P6-L2-6', 'P6-L2-6', 2, '94-105-116', false, false, NOW(), NOW()),
       (117, 6, 7, 106, 'P6-L2-7', 'P6-L2-7', 2, '95-106-117', false, false, NOW(), NOW()),
       (118, 6, 8, 107, 'P6-L2-8', 'P6-L2-8', 2, '96-107-118', false, false, NOW(), NOW()),
       (119, 6, 9, 108, 'P6-L2-9', 'P6-L2-9', 2, '97-108-119', false, false, NOW(), NOW()),
       (120, 6, 10, 110, 'P6-L2-10', 'P6-L2-10', 2, '100-110-120', false, false, NOW(), NOW());

-- Post 7 (총 12개 댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     path, is_deleted, is_blocked, created_at, updated_at)
VALUES (121, 7, 1, NULL, 'P7-L0-1', 'P7-L0-1', 0, '121', false, false, NOW(), NOW()),
       (122, 7, 2, NULL, 'P7-L0-2', 'P7-L0-2', 0, '122', false, false, NOW(), NOW()),
       (123, 7, 3, NULL, 'P7-L0-3', 'P7-L0-3', 0, '123', false, false, NOW(), NOW()),
       (124, 7, 4, NULL, 'P7-L0-4', 'P7-L0-4', 0, '124', false, false, NOW(), NOW()),
       (125, 7, 5, 121, 'P7-L1-1', 'P7-L1-1', 1, '121-125', false, false, NOW(), NOW()),
       (126, 7, 6, 122, 'P7-L1-2', 'P7-L1-2', 1, '122-126', false, false, NOW(), NOW()),
       (127, 7, 7, 123, 'P7-L1-3', 'P7-L1-3', 1, '123-127', false, false, NOW(), NOW()),
       (128, 7, 8, 124, 'P7-L1-4', 'P7-L1-4', 1, '124-128', false, false, NOW(), NOW()),
       (129, 7, 9, 125, 'P7-L2-1', 'P7-L2-1', 2, '121-125-129', false, false, NOW(), NOW()),
       (130, 7, 10, 126, 'P7-L2-2', 'P7-L2-2', 2, '122-126-130', false, false, NOW(), NOW()),
       (131, 7, 1, 127, 'P7-L2-3', 'P7-L2-3', 2, '123-127-131', false, false, NOW(), NOW()),
       (132, 7, 2, 128, 'P7-L2-4', 'P7-L2-4', 2, '124-128-132', false, false, NOW(), NOW());

-- Post 8 (총 28개 댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     path, is_deleted, is_blocked, created_at, updated_at)
VALUES (133, 8, 1, NULL, 'P8-L0-1', 'P8-L0-1', 0, '133', false, false, NOW(), NOW()),
       (134, 8, 2, NULL, 'P8-L0-2', 'P8-L0-2', 0, '134', false, false, NOW(), NOW()),
       (135, 8, 3, NULL, 'P8-L0-3', 'P8-L0-3', 0, '135', false, false, NOW(), NOW()),
       (136, 8, 4, NULL, 'P8-L0-4', 'P8-L0-4', 0, '136', false, false, NOW(), NOW()),
       (137, 8, 5, NULL, 'P8-L0-5', 'P8-L0-5', 0, '137', false, false, NOW(), NOW()),
       (138, 8, 6, NULL, 'P8-L0-6', 'P8-L0-6', 0, '138', false, false, NOW(), NOW()),
       (139, 8, 7, NULL, 'P8-L0-7', 'P8-L0-7', 0, '139', false, false, NOW(), NOW()),
       (140, 8, 8, NULL, 'P8-L0-8', 'P8-L0-8', 0, '140', false, false, NOW(), NOW()),
       (141, 8, 9, 133, 'P8-L1-1', 'P8-L1-1', 1, '133-141', false, false, NOW(), NOW()),
       (142, 8, 10, 133, 'P8-L1-2', 'P8-L1-2', 1, '133-142', false, false, NOW(), NOW()),
       (143, 8, 1, 134, 'P8-L1-3', 'P8-L1-3', 1, '134-143', false, false, NOW(), NOW()),
       (144, 8, 2, 135, 'P8-L1-4', 'P8-L1-4', 1, '135-144', false, false, NOW(), NOW()),
       (145, 8, 3, 136, 'P8-L1-5', 'P8-L1-5', 1, '136-145', false, false, NOW(), NOW()),
       (146, 8, 4, 137, 'P8-L1-6', 'P8-L1-6', 1, '137-146', false, false, NOW(), NOW()),
       (147, 8, 5, 138, 'P8-L1-7', 'P8-L1-7', 1, '138-147', false, false, NOW(), NOW()),
       (148, 8, 6, 139, 'P8-L1-8', 'P8-L1-8', 1, '139-148', false, false, NOW(), NOW()),
       (149, 8, 7, 140, 'P8-L1-9', 'P8-L1-9', 1, '140-149', false, false, NOW(), NOW()),
       (150, 8, 8, 141, 'P8-L2-1', 'P8-L2-1', 2, '133-141-150', false, false, NOW(), NOW()),
       (151, 8, 9, 141, 'P8-L2-2', 'P8-L2-2', 2, '133-141-151', false, false, NOW(), NOW()),
       (152, 8, 10, 142, 'P8-L2-3', 'P8-L2-3', 2, '133-142-152', false, false, NOW(), NOW()),
       (153, 8, 1, 143, 'P8-L2-4', 'P8-L2-4', 2, '134-143-153', false, false, NOW(), NOW()),
       (154, 8, 2, 144, 'P8-L2-5', 'P8-L2-5', 2, '135-144-154', false, false, NOW(), NOW()),
       (155, 8, 3, 145, 'P8-L2-6', 'P8-L2-6', 2, '136-145-155', false, false, NOW(), NOW()),
       (156, 8, 4, 146, 'P8-L2-7', 'P8-L2-7', 2, '137-146-156', false, false, NOW(), NOW()),
       (157, 8, 5, 147, 'P8-L2-8', 'P8-L2-8', 2, '138-147-157', false, false, NOW(), NOW()),
       (158, 8, 6, 148, 'P8-L2-9', 'P8-L2-9', 2, '139-148-158', false, false, NOW(), NOW()),
       (159, 8, 7, 149, 'P8-L2-10', 'P8-L2-10', 2, '140-149-159', false, false, NOW(), NOW()),
       (160, 8, 8, 149, 'P8-L2-11', 'P8-L2-11', 2, '140-149-160', false, false, NOW(), NOW());

-- Post 9 (총 20개 댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     path, is_deleted, is_blocked, created_at, updated_at)
VALUES (161, 9, 1, NULL, 'P9-L0-1', 'P9-L0-1', 0, '161', false, false, NOW(), NOW()),
       (162, 9, 2, NULL, 'P9-L0-2', 'P9-L0-2', 0, '162', false, false, NOW(), NOW()),
       (163, 9, 3, NULL, 'P9-L0-3', 'P9-L0-3', 0, '163', false, false, NOW(), NOW()),
       (164, 9, 4, NULL, 'P9-L0-4', 'P9-L0-4', 0, '164', false, false, NOW(), NOW()),
       (165, 9, 5, NULL, 'P9-L0-5', 'P9-L0-5', 0, '165', false, false, NOW(), NOW()),
       (166, 9, 6, NULL, 'P9-L0-6', 'P9-L0-6', 0, '166', false, false, NOW(), NOW()),
       (167, 9, 7, 161, 'P9-L1-1', 'P9-L1-1', 1, '161-167', false, false, NOW(), NOW()),
       (168, 9, 8, 161, 'P9-L1-2', 'P9-L1-2', 1, '161-168', false, false, NOW(), NOW()),
       (169, 9, 9, 162, 'P9-L1-3', 'P9-L1-3', 1, '162-169', false, false, NOW(), NOW()),
       (170, 9, 10, 163, 'P9-L1-4', 'P9-L1-4', 1, '163-170', false, false, NOW(), NOW()),
       (171, 9, 1, 164, 'P9-L1-5', 'P9-L1-5', 1, '164-171', false, false, NOW(), NOW()),
       (172, 9, 2, 165, 'P9-L1-6', 'P9-L1-6', 1, '165-172', false, false, NOW(), NOW()),
       (173, 9, 3, 166, 'P9-L1-7', 'P9-L1-7', 1, '166-173', false, false, NOW(), NOW()),
       (174, 9, 4, 167, 'P9-L2-1', 'P9-L2-1', 2, '161-167-174', false, false, NOW(), NOW()),
       (175, 9, 5, 168, 'P9-L2-2', 'P9-L2-2', 2, '161-168-175', false, false, NOW(), NOW()),
       (176, 9, 6, 169, 'P9-L2-3', 'P9-L2-3', 2, '162-169-176', false, false, NOW(), NOW()),
       (177, 9, 7, 170, 'P9-L2-4', 'P9-L2-4', 2, '163-170-177', false, false, NOW(), NOW()),
       (178, 9, 8, 171, 'P9-L2-5', 'P9-L2-5', 2, '164-171-178', false, false, NOW(), NOW()),
       (179, 9, 9, 172, 'P9-L2-6', 'P9-L2-6', 2, '165-172-179', false, false, NOW(), NOW()),
       (180, 9, 10, 173, 'P9-L2-7', 'P9-L2-7', 2, '166-173-180', false, false, NOW(), NOW());

-- Post 10 (총 20개 댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     path, is_deleted, is_blocked, created_at, updated_at)
VALUES (181, 10, 1, NULL, 'P10-L0-1', 'P10-L0-1', 0, '181', false, false, NOW(), NOW()),
       (182, 10, 2, NULL, 'P10-L0-2', 'P10-L0-2', 0, '182', false, false, NOW(), NOW()),
       (183, 10, 3, NULL, 'P10-L0-3', 'P10-L0-3', 0, '183', false, false, NOW(), NOW()),
       (184, 10, 4, NULL, 'P10-L0-4', 'P10-L0-4', 0, '184', false, false, NOW(), NOW()),
       (185, 10, 5, NULL, 'P10-L0-5', 'P10-L0-5', 0, '185', false, false, NOW(), NOW()),
       (186, 10, 6, NULL, 'P10-L0-6', 'P10-L0-6', 0, '186', false, false, NOW(), NOW()),
       (187, 10, 7, 181, 'P10-L1-1', 'P10-L1-1', 1, '181-187', false, false, NOW(), NOW()),
       (188, 10, 8, 181, 'P10-L1-2', 'P10-L1-2', 1, '181-188', false, false, NOW(), NOW()),
       (189, 10, 9, 182, 'P10-L1-3', 'P10-L1-3', 1, '182-189', false, false, NOW(), NOW()),
       (190, 10, 10, 183, 'P10-L1-4', 'P10-L1-4', 1, '183-190', false, false, NOW(), NOW()),
       (191, 10, 1, 184, 'P10-L1-5', 'P10-L1-5', 1, '184-191', false, false, NOW(), NOW()),
       (192, 10, 2, 185, 'P10-L1-6', 'P10-L1-6', 1, '185-192', false, false, NOW(), NOW()),
       (193, 10, 3, 186, 'P10-L1-7', 'P10-L1-7', 1, '186-193', false, false, NOW(), NOW()),
       (194, 10, 4, 187, 'P10-L2-1', 'P10-L2-1', 2, '181-187-194', false, false, NOW(), NOW()),
       (195, 10, 5, 188, 'P10-L2-2', 'P10-L2-2', 2, '181-188-195', false, false, NOW(), NOW()),
       (196, 10, 6, 189, 'P10-L2-3', 'P10-L2-3', 2, '182-189-196', false, false, NOW(), NOW()),
       (197, 10, 7, 190, 'P10-L2-4', 'P10-L2-4', 2, '183-190-197', false, false, NOW(), NOW()),
       (198, 10, 8, 191, 'P10-L2-5', 'P10-L2-5', 2, '184-191-198', false, false, NOW(), NOW()),
       (199, 10, 9, 192, 'P10-L2-6', 'P10-L2-6', 2, '185-192-199', false, false, NOW(), NOW()),
       (200, 10, 10, 193, 'P10-L2-7', 'P10-L2-7', 2, '186-193-200', false, false, NOW(), NOW());

-- ranking
INSERT INTO rankings(id, member_id, ranks, score, period_type, category, previous_rank,
                     created_at) value (1, 1, 1, 1000, 'DAILY', 'POST', NULL, '2025-07-13 00:00:00'),
    (2, 2, 2, 900, 'DAILY', 'POST', NULL, '2025-07-13 23:59:59'),
    (3, 3, 3, 800, 'DAILY', 'POST', NULL, '2025-07-14 00:00:00'),
    (4, 4, 4, 700, 'DAILY', 'POST', NULL, NOW()),
    (5, 5, 5, 600, 'DAILY', 'POST', NULL, NOW()),
    (6, 6, 6, 500, 'DAILY', 'POST', NULL, NOW()),
    (7, 7, 7, 400, 'DAILY', 'POST', NULL, NOW()),
    (8, 8, 8, 300, 'DAILY', 'POST', NULL, NOW()),
    (9, 9, 9, 200, 'DAILY', 'POST', NULL, NOW()),
    (10, 10, 10, 100, 'DAILY', 'POST', NULL, NOW());

-- Member 1의 10일치 DAILY POST 랭킹 데이터 (최근 10일)
-- 각 날짜별로 다른 점수와 순위를 가지도록 설정
INSERT INTO rankings(id, member_id, ranks, score, period_type, category, previous_rank, created_at)
VALUES
-- 오늘 (가장 높은 점수)
(11, 1, 1, 950, 'DAILY', 'POST', 2, DATE_SUB(NOW(), INTERVAL 0 DAY)),

-- 1일 전
(12, 1, 2, 870, 'DAILY', 'POST', 3, DATE_SUB(NOW(), INTERVAL 1 DAY)),

-- 2일 전
(13, 1, 3, 820, 'DAILY', 'POST', 1, DATE_SUB(NOW(), INTERVAL 2 DAY)),

-- 3일 전
(14, 1, 1, 910, 'DAILY', 'POST', 4, DATE_SUB(NOW(), INTERVAL 3 DAY)),

-- 4일 전
(15, 1, 4, 750, 'DAILY', 'POST', 2, DATE_SUB(NOW(), INTERVAL 4 DAY)),

-- 5일 전
(16, 1, 2, 880, 'DAILY', 'POST', 5, DATE_SUB(NOW(), INTERVAL 5 DAY)),

-- 6일 전
(17, 1, 5, 680, 'DAILY', 'POST', 3, DATE_SUB(NOW(), INTERVAL 6 DAY)),

-- 7일 전
(18, 1, 3, 800, 'DAILY', 'POST', 6, DATE_SUB(NOW(), INTERVAL 7 DAY)),

-- 8일 전
(19, 1, 6, 620, 'DAILY', 'POST', 4, DATE_SUB(NOW(), INTERVAL 8 DAY)),

-- 9일 전 (가장 낮은 점수)
(20, 1, 7, 580, 'DAILY', 'POST', 7, DATE_SUB(NOW(), INTERVAL 9 DAY));

INSERT INTO profanities (original, replacement, description, category, created_at, updated_at)
VALUES
-- GENERAL_SWEAR
('씨발', '아이고', '일반적인 욕설 표현', 'GENERAL_SWEAR', NOW(), NOW()),
('좆', '이런', '남성 성기 관련 비속어', 'GENERAL_SWEAR', NOW(), NOW()),
('병신', '멍청이', '비하적 욕설 표현', 'GENERAL_SWEAR', NOW(), NOW()),
('개새끼', '나쁜 사람', '동물에 빗댄 욕설', 'GENERAL_SWEAR', NOW(), NOW()),
('존나', '매우', '강조 표현의 욕설화', 'GENERAL_SWEAR', NOW(), NOW()),

-- SEXUAL_DEGRADATION
('보지', '성기', '여성 성기 비속어', 'SEXUAL_DEGRADATION', NOW(), NOW()),
('자지', '성기', '남성 성기 비속어', 'SEXUAL_DEGRADATION', NOW(), NOW()),
('떡치다', '관계하다', '성행위 은어', 'SEXUAL_DEGRADATION', NOW(), NOW()),
('섹스', '관계', '성행위를 직접 지칭', 'SEXUAL_DEGRADATION', NOW(), NOW()),
('딸딸이', '자위', '남성 자위 비속어', 'SEXUAL_DEGRADATION', NOW(), NOW()),

-- DISCRIMINATION_HATE
('틀딱', '어르신', '노인을 비하하는 표현', 'DISCRIMINATION_HATE', NOW(), NOW()),
('급식충', '학생', '학생 비하 표현', 'DISCRIMINATION_HATE', NOW(), NOW()),
('흑형', '흑인 남성', '흑인 대상 비하', 'DISCRIMINATION_HATE', NOW(), NOW()),
('김치녀', '여성', '한국 여성 비하 표현', 'DISCRIMINATION_HATE', NOW(), NOW()),
('정신병자', '정신질환자', '정신질환 비하', 'DISCRIMINATION_HATE', NOW(), NOW()),

-- MODIFIED_SWEAR
('ㅅㅂ', '아이고', '초성 욕설', 'MODIFIED_SWEAR', NOW(), NOW()),
('ㅂㅅ', '멍청이', '초성 비하', 'MODIFIED_SWEAR', NOW(), NOW()),
('시@발', '아이고', '우회 욕설', 'MODIFIED_SWEAR', NOW(), NOW()),
('fuxk', 'dang', '영어 욕설 우회 표현', 'MODIFIED_SWEAR', NOW(), NOW()),
('tlqkf', '젠장', '한글 자판 치환 욕설', 'MODIFIED_SWEAR', NOW(), NOW());

-- === Reaction 더미 데이터 생성 ===
-- member_reactions 테이블에 게시글 및 댓글 반응 데이터를 생성합니다.
-- 제약 조건: 한 회원은 하나의 게시글 또는 댓글에 대해 오직 하나의 반응만 가능
-- Emotion: HAPPY, SAD, ANGRY, EXCITED, CONFUSED, PROUD

-- === 게시글 반응 데이터 (Post Reactions) ===
-- Post 1~10에 대한 반응들 (총 50개)

-- Post 1에 대한 반응 (5개)
INSERT INTO member_reactions (id, member_id, post_id, comment_id, type, created_at, updated_at)
VALUES (1, 2, 1, NULL, 'HAPPY', NOW(), NOW()),
       (2, 3, 1, NULL, 'EXCITED', NOW(), NOW()),
       (3, 4, 1, NULL, 'PROUD', NOW(), NOW()),
       (4, 5, 1, NULL, 'HAPPY', NOW(), NOW()),
       (5, 6, 1, NULL, 'EXCITED', NOW(), NOW());

-- Post 2에 대한 반응 (6개)
INSERT INTO member_reactions (id, member_id, post_id, comment_id, type, created_at, updated_at)
VALUES (6, 1, 2, NULL, 'SAD', NOW(), NOW()),
       (7, 3, 2, NULL, 'CONFUSED', NOW(), NOW()),
       (8, 4, 2, NULL, 'SAD', NOW(), NOW()),
       (9, 7, 2, NULL, 'HAPPY', NOW(), NOW()),
       (10, 8, 2, NULL, 'PROUD', NOW(), NOW()),
       (11, 9, 2, NULL, 'EXCITED', NOW(), NOW());

-- Post 3에 대한 반응 (4개)
INSERT INTO member_reactions (id, member_id, post_id, comment_id, type, created_at, updated_at)
VALUES (12, 2, 3, NULL, 'ANGRY', NOW(), NOW()),
       (13, 5, 3, NULL, 'ANGRY', NOW(), NOW()),
       (14, 8, 3, NULL, 'CONFUSED', NOW(), NOW()),
       (15, 10, 3, NULL, 'SAD', NOW(), NOW());

-- Post 4에 대한 반응 (7개)
INSERT INTO member_reactions (id, member_id, post_id, comment_id, type, created_at, updated_at)
VALUES (16, 1, 4, NULL, 'EXCITED', NOW(), NOW()),
       (17, 2, 4, NULL, 'HAPPY', NOW(), NOW()),
       (18, 3, 4, NULL, 'EXCITED', NOW(), NOW()),
       (19, 6, 4, NULL, 'PROUD', NOW(), NOW()),
       (20, 7, 4, NULL, 'HAPPY', NOW(), NOW()),
       (21, 9, 4, NULL, 'EXCITED', NOW(), NOW()),
       (22, 10, 4, NULL, 'HAPPY', NOW(), NOW());

-- Post 5에 대한 반응 (3개)
INSERT INTO member_reactions (id, member_id, post_id, comment_id, type, created_at, updated_at)
VALUES (23, 2, 5, NULL, 'CONFUSED', NOW(), NOW()),
       (24, 4, 5, NULL, 'CONFUSED', NOW(), NOW()),
       (25, 8, 5, NULL, 'SAD', NOW(), NOW());

-- Post 6에 대한 반응 (5개)
INSERT INTO member_reactions (id, member_id, post_id, comment_id, type, created_at, updated_at)
VALUES (26, 3, 6, NULL, 'PROUD', NOW(), NOW()),
       (27, 5, 6, NULL, 'HAPPY', NOW(), NOW()),
       (28, 7, 6, NULL, 'PROUD', NOW(), NOW()),
       (29, 9, 6, NULL, 'EXCITED', NOW(), NOW()),
       (30, 10, 6, NULL, 'PROUD', NOW(), NOW());

-- Post 7에 대한 반응 (6개)
INSERT INTO member_reactions (id, member_id, post_id, comment_id, type, created_at, updated_at)
VALUES (31, 2, 7, NULL, 'HAPPY', NOW(), NOW()),
       (32, 4, 7, NULL, 'EXCITED', NOW(), NOW()),
       (33, 6, 7, NULL, 'HAPPY', NOW(), NOW()),
       (34, 8, 7, NULL, 'PROUD', NOW(), NOW()),
       (35, 9, 7, NULL, 'HAPPY', NOW(), NOW()),
       (36, 10, 7, NULL, 'EXCITED', NOW(), NOW());

-- Post 8에 대한 반응 (4개)
INSERT INTO member_reactions (id, member_id, post_id, comment_id, type, created_at, updated_at)
VALUES (37, 2, 8, NULL, 'SAD', NOW(), NOW()),
       (38, 3, 8, NULL, 'CONFUSED', NOW(), NOW()),
       (39, 5, 8, NULL, 'SAD', NOW(), NOW()),
       (40, 7, 8, NULL, 'ANGRY', NOW(), NOW());

-- Post 9에 대한 반응 (5개)
INSERT INTO member_reactions (id, member_id, post_id, comment_id, type, created_at, updated_at)
VALUES (41, 1, 9, NULL, 'ANGRY', NOW(), NOW()),
       (42, 3, 9, NULL, 'PROUD', NOW(), NOW()),
       (43, 6, 9, NULL, 'ANGRY', NOW(), NOW()),
       (44, 8, 9, NULL, 'CONFUSED', NOW(), NOW()),
       (45, 10, 9, NULL, 'ANGRY', NOW(), NOW());

-- Post 10에 대한 반응 (5개)
INSERT INTO member_reactions (id, member_id, post_id, comment_id, type, created_at, updated_at)
VALUES (46, 2, 10, NULL, 'EXCITED', NOW(), NOW()),
       (47, 4, 10, NULL, 'HAPPY', NOW(), NOW()),
       (48, 6, 10, NULL, 'EXCITED', NOW(), NOW()),
       (49, 8, 10, NULL, 'PROUD', NOW(), NOW()),
       (50, 9, 10, NULL, 'EXCITED', NOW(), NOW());

-- === 댓글 반응 데이터 (Comment Reactions) ===
-- 주요 댓글들에 대한 반응 (총 30개)

-- Post 1의 댓글들에 대한 반응
INSERT INTO member_reactions (id, member_id, post_id, comment_id, type, created_at, updated_at)
VALUES
-- Comment 1에 대한 반응 (member 2가 작성한 댓글이므로 본인 제외)
(51, 1, NULL, 1, 'HAPPY', NOW(), NOW()),
(52, 4, NULL, 1, 'PROUD', NOW(), NOW()),
(53, 7, NULL, 1, 'EXCITED', NOW(), NOW()),

-- Comment 2에 대한 반응 (member 3이 작성한 댓글이므로 본인 제외)
(54, 2, NULL, 2, 'HAPPY', NOW(), NOW()),
(55, 5, NULL, 2, 'PROUD', NOW(), NOW()),

-- Comment 3에 대한 반응 (member 4가 작성한 댓글이므로 본인 제외)
(56, 1, NULL, 3, 'CONFUSED', NOW(), NOW()),
(57, 6, NULL, 3, 'HAPPY', NOW(), NOW()),

-- Comment 5에 대한 반응 (member 6이 작성한 댓글이므로 본인 제외)
(58, 3, NULL, 5, 'EXCITED', NOW(), NOW()),
(59, 8, NULL, 5, 'HAPPY', NOW(), NOW());

-- Post 2의 댓글들에 대한 반응
INSERT INTO member_reactions (id, member_id, post_id, comment_id, type, created_at, updated_at)
VALUES
-- Comment 16에 대한 반응 (member 1이 작성한 댓글이므로 본인 제외)
(60, 2, NULL, 16, 'HAPPY', NOW(), NOW()),
(61, 5, NULL, 16, 'EXCITED', NOW(), NOW()),
(62, 8, NULL, 16, 'PROUD', NOW(), NOW()),

-- Comment 17에 대한 반응 (member 2가 작성한 댓글이므로 본인 제외)
(63, 4, NULL, 17, 'EXCITED', NOW(), NOW()),
(64, 7, NULL, 17, 'HAPPY', NOW(), NOW()),

-- Comment 20에 대한 반응 (member 5가 작성한 댓글이므로 본인 제외)
(65, 1, NULL, 20, 'EXCITED', NOW(), NOW()),
(66, 6, NULL, 20, 'HAPPY', NOW(), NOW()),

-- Comment 23에 대한 반응 (member 8이 작성한 댓글이므로 본인 제외)
(67, 3, NULL, 23, 'PROUD', NOW(), NOW()),
(68, 9, NULL, 23, 'EXCITED', NOW(), NOW());

-- Post 6의 댓글들에 대한 반응 (가장 많은 댓글을 가진 포스트)
INSERT INTO member_reactions (id, member_id, post_id, comment_id, type, created_at, updated_at)
VALUES
-- Comment 91에 대한 반응
(69, 2, NULL, 91, 'HAPPY', NOW(), NOW()),
(70, 4, NULL, 91, 'EXCITED', NOW(), NOW()),
(71, 8, NULL, 91, 'PROUD', NOW(), NOW()),

-- Comment 92에 대한 반응
(72, 1, NULL, 92, 'HAPPY', NOW(), NOW()),
(73, 5, NULL, 92, 'EXCITED', NOW(), NOW()),

-- Comment 95에 대한 반응
(74, 3, NULL, 95, 'PROUD', NOW(), NOW()),
(75, 7, NULL, 95, 'HAPPY', NOW(), NOW()),

-- Comment 100에 대한 반응
(76, 2, NULL, 100, 'EXCITED', NOW(), NOW()),
(77, 6, NULL, 100, 'HAPPY', NOW(), NOW()),

-- Comment 105에 대한 반응 (대댓글)
(78, 1, NULL, 105, 'CONFUSED', NOW(), NOW()),
(79, 8, NULL, 105, 'SAD', NOW(), NOW()),

-- Comment 110에 대한 반응 (대댓글)
(80, 3, NULL, 110, 'ANGRY', NOW(), NOW());

-- === 배지 초기 데이터 삽입 ===
INSERT INTO badges (name, description, image_url, created_at, updated_at)
VALUES ('첫 게시글', '첫 번째 게시글을 작성한 사용자에게 주어지는 배지입니다.', NULL, NOW(), NOW()),
       ('새로운 가족', '회원가입을 완료한 새로운 멤버에게 주어지는 배지입니다.', NULL, NOW(), NOW()),
       ('신진 작가', '게시글을 10개 이상 작성하는 사용자에게 주어지는 배지입니다.', NULL, NOW(), NOW()),
       ('소통의 시작', '첫 번째 댓글을 작성한 사용자에게 주어지는 배지입니다.', NULL, NOW(), NOW()),
       ('리액션 요정', '댓글을 5회 이상 작성한 사용자에게 주어지는 배지입니다.', NULL, NOW(), NOW()),
       ('인기 게시글', '작성한 게시글에 댓글이 5개 이상 달린 사용자에게 주어지는 배지입니다.', NULL, NOW(), NOW()),
       ('첫 반응하기', '첫 번째 반응하기를 실행한 사용자에게 주어지는 배지입니다.', NULL, NOW(), NOW()),
       ('다정한 이웃', '반응하기를 10회 이상 실행한 사용자에게 주어지는 배지입니다.', NULL, NOW(), NOW()),
       ('첫 랭킹 진입', '첫 번째로 랭킹에 진입한 사용자에게 주어지는 배지입니다.', NULL, NOW(), NOW());


-- === Report 더미 데이터 50개 ==
-- reasonDetail은 reasonType이 OTHER일 때만 값을 설정하고, 나머지는 NULL
-- 게시글 신고와 댓글 신고를 적절히 분배하여 생성
INSERT INTO reports (id, reporter_id, post_id, comment_id, reason_type, reason_detail, status_type,
                     created_at, updated_at)
VALUES
-- 게시글 신고 30개
(1, 2, 1, NULL, 'SPAM', NULL, 'PENDING', NOW(), NOW()),
(2, 3, 5, NULL, 'INAPPROPRIATE_CONTENT', NULL, 'ACCEPTED', NOW(), NOW()),
(3, 4, 10, NULL, 'PORNOGRAPHIC_CONTENT', NULL, 'REJECTED', NOW(), NOW()),
(4, 5, 15, NULL, 'ILLEGAL_FILMING', NULL, 'PENDING', NOW(), NOW()),
(5, 6, 20, NULL, 'OTHER', '부적절한 광고성 내용이 포함되어 있습니다', 'PENDING', NOW(), NOW()),
(6, 7, 25, NULL, 'SPAM', NULL, 'ACCEPTED', NOW(), NOW()),
(7, 8, 30, NULL, 'INAPPROPRIATE_CONTENT', NULL, 'POSTPONE', NOW(), NOW()),
(8, 9, 35, NULL, 'PORNOGRAPHIC_CONTENT', NULL, 'REJECTED', NOW(), NOW()),
(9, 10, 40, NULL, 'ILLEGAL_FILMING', NULL, 'PENDING', NOW(), NOW()),
(10, 1, 45, NULL, 'OTHER', '개인정보가 노출되어 있습니다', 'ACCEPTED', NOW(), NOW()),
(11, 2, 50, NULL, 'SPAM', NULL, 'PENDING', NOW(), NOW()),
(12, 3, 55, NULL, 'INAPPROPRIATE_CONTENT', NULL, 'REJECTED', NOW(), NOW()),
(13, 4, 60, NULL, 'PORNOGRAPHIC_CONTENT', NULL, 'POSTPONE', NOW(), NOW()),
(14, 5, 65, NULL, 'ILLEGAL_FILMING', NULL, 'PENDING', NOW(), NOW()),
(15, 6, 70, NULL, 'OTHER', '폭력적인 내용이 포함되어 있습니다', 'ACCEPTED', NOW(), NOW()),
(16, 7, 75, NULL, 'SPAM', NULL, 'REJECTED', NOW(), NOW()),
(17, 8, 80, NULL, 'INAPPROPRIATE_CONTENT', NULL, 'PENDING', NOW(), NOW()),
(18, 9, 85, NULL, 'PORNOGRAPHIC_CONTENT', NULL, 'POSTPONE', NOW(), NOW()),
(19, 10, 90, NULL, 'ILLEGAL_FILMING', NULL, 'ACCEPTED', NOW(), NOW()),
(20, 1, 95, NULL, 'OTHER', '저작권 침해가 의심됩니다', 'PENDING', NOW(), NOW()),
(21, 2, 100, NULL, 'SPAM', NULL, 'REJECTED', NOW(), NOW()),
(22, 3, 105, NULL, 'INAPPROPRIATE_CONTENT', NULL, 'PENDING', NOW(), NOW()),
(23, 4, 110, NULL, 'PORNOGRAPHIC_CONTENT', NULL, 'ACCEPTED', NOW(), NOW()),
(24, 5, 115, NULL, 'ILLEGAL_FILMING', NULL, 'POSTPONE', NOW(), NOW()),
(25, 6, 120, NULL, 'OTHER', '사기성 내용이 포함되어 있습니다', 'REJECTED', NOW(), NOW()),
(26, 7, 125, NULL, 'SPAM', NULL, 'PENDING', NOW(), NOW()),
(27, 8, 130, NULL, 'INAPPROPRIATE_CONTENT', NULL, 'ACCEPTED', NOW(), NOW()),
(28, 9, 135, NULL, 'PORNOGRAPHIC_CONTENT', NULL, 'PENDING', NOW(), NOW()),
(29, 10, 140, NULL, 'ILLEGAL_FILMING', NULL, 'REJECTED', NOW(), NOW()),
(30, 1, 145, NULL, 'OTHER', '혐오 표현이 포함되어 있습니다', 'POSTPONE', NOW(), NOW()),

-- 댓글 신고 20개
(31, 2, NULL, 1, 'SPAM', NULL, 'PENDING', NOW(), NOW()),
(32, 3, NULL, 5, 'INAPPROPRIATE_CONTENT', NULL, 'ACCEPTED', NOW(), NOW()),
(33, 4, NULL, 10, 'PORNOGRAPHIC_CONTENT', NULL, 'REJECTED', NOW(), NOW()),
(34, 5, NULL, 15, 'ILLEGAL_FILMING', NULL, 'PENDING', NOW(), NOW()),
(35, 6, NULL, 20, 'OTHER', '비방성 발언이 포함되어 있습니다', 'ACCEPTED', NOW(), NOW()),
(36, 7, NULL, 25, 'SPAM', NULL, 'POSTPONE', NOW(), NOW()),
(37, 8, NULL, 30, 'INAPPROPRIATE_CONTENT', NULL, 'REJECTED', NOW(), NOW()),
(38, 9, NULL, 35, 'PORNOGRAPHIC_CONTENT', NULL, 'PENDING', NOW(), NOW()),
(39, 10, NULL, 40, 'ILLEGAL_FILMING', NULL, 'ACCEPTED', NOW(), NOW()),
(40, 1, NULL, 45, 'OTHER', '개인정보 유출이 의심됩니다', 'PENDING', NOW(), NOW()),
(41, 2, NULL, 50, 'SPAM', NULL, 'REJECTED', NOW(), NOW()),
(42, 3, NULL, 55, 'INAPPROPRIATE_CONTENT', NULL, 'POSTPONE', NOW(), NOW()),
(43, 4, NULL, 60, 'PORNOGRAPHIC_CONTENT', NULL, 'ACCEPTED', NOW(), NOW()),
(44, 5, NULL, 65, 'ILLEGAL_FILMING', NULL, 'PENDING', NOW(), NOW()),
(45, 6, NULL, 70, 'OTHER', '폭력적인 언어가 사용되었습니다', 'REJECTED', NOW(), NOW()),
(46, 7, NULL, 75, 'SPAM', NULL, 'ACCEPTED', NOW(), NOW()),
(47, 8, NULL, 80, 'INAPPROPRIATE_CONTENT', NULL, 'PENDING', NOW(), NOW()),
(48, 9, NULL, 85, 'PORNOGRAPHIC_CONTENT', NULL, 'POSTPONE', NOW(), NOW()),
(49, 10, NULL, 90, 'ILLEGAL_FILMING', NULL, 'REJECTED', NOW(), NOW()),
(50, 1, NULL, 95, 'OTHER', '허위 사실을 유포하고 있습니다', 'ACCEPTED', NOW(), NOW());