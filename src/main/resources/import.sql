INSERT INTO PLAYER (ID, FIRST_NAME, LAST_NAME, USER_NAME) VALUES (1,'Jack', 'Bauer', 'j.bauer@ctu.gov')
INSERT INTO PLAYER (ID, FIRST_NAME, LAST_NAME, USER_NAME) VALUES (2,'Chloe','OBrian', 'c.obrian@ctu.gov')
INSERT INTO PLAYER (ID, FIRST_NAME, LAST_NAME, USER_NAME) VALUES (3,'Kim', 'Bauer', 'kim_bauer@gmail.com')
INSERT INTO PLAYER (ID, FIRST_NAME, LAST_NAME, USER_NAME) VALUES (4,'Tony', 'Almeida', 't.almeida@ctu.gov')


INSERT INTO GAME (ID, CREATION_DATE) VALUES (1,'2019-08-03T03:15:00')
INSERT INTO GAME (ID, CREATION_DATE) VALUES (2,'2019-08-03T04:15:00')
INSERT INTO GAME (ID, CREATION_DATE) VALUES (3,'2019-08-03T05:15:00')


INSERT INTO GAME_PLAYER (ID, CREATION_DATE, PLAYER_ID, GAME_ID) VALUES (1, '2019-08-03T03:15:00', 1, 1)
INSERT INTO GAME_PLAYER (ID, CREATION_DATE, PLAYER_ID, GAME_ID) VALUES (2, '2019-08-03T03:15:00', 2, 1)
INSERT INTO GAME_PLAYER (ID, CREATION_DATE, PLAYER_ID, GAME_ID) VALUES (3, '2019-08-03T04:15:00', 2, 2)
INSERT INTO GAME_PLAYER (ID, CREATION_DATE, PLAYER_ID, GAME_ID) VALUES (4, '2019-08-03T04:15:00', 3, 2)