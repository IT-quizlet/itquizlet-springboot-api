-- User Table
CREATE TABLE "User" (
                        id UUID PRIMARY KEY,
                        username VARCHAR(100) NOT NULL,
                        email VARCHAR(150) UNIQUE NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        avatar VARCHAR(255),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Quiz Table
CREATE TABLE "Quiz" (
                        id UUID PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        description TEXT,
                        tags TEXT[],
                        owner UUID NOT NULL REFERENCES "User"(id) ON DELETE CASCADE,
                        estimated_time INT
);

-- Question Table
CREATE TABLE "Question" (
                            id UUID PRIMARY KEY,
                            text TEXT NOT NULL,
                            options TEXT[],
                            type VARCHAR(50) NOT NULL,
                            correct_answer TEXT NOT NULL,
                            sources TEXT[]
);

-- Response Table
CREATE TABLE "Response" (
                            id UUID PRIMARY KEY,
                            user_id UUID NOT NULL REFERENCES "User"(id) ON DELETE CASCADE,
                            quiz_id UUID NOT NULL REFERENCES "Quiz"(id) ON DELETE CASCADE,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Answer Table
CREATE TABLE "Answer" (
                          id UUID PRIMARY KEY,
                          response_id UUID NOT NULL REFERENCES "Response"(id) ON DELETE CASCADE,
                          question_id UUID NOT NULL REFERENCES "Question"(id) ON DELETE CASCADE,
                          answer TEXT NOT NULL
);

-- User-Saved Quizzes Many-to-Many Relationship
CREATE TABLE "User_Saved_Quizzes" (
                                      user_id UUID NOT NULL REFERENCES "User"(id) ON DELETE CASCADE,
                                      quiz_id UUID NOT NULL REFERENCES "Quiz"(id) ON DELETE CASCADE,
                                      PRIMARY KEY (user_id, quiz_id)
);

-- Quiz-Questions Many-to-Many Relationship
CREATE TABLE "Quiz_Questions" (
                                  quiz_id UUID NOT NULL REFERENCES "Quiz"(id) ON DELETE CASCADE,
                                  question_id UUID NOT NULL REFERENCES "Question"(id) ON DELETE CASCADE,
                                  PRIMARY KEY (quiz_iquestion_id)
);
