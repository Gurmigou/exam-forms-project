export const isPositiveNumber = (num: string): boolean => {
    if (num === "0")
        return false;
    if (num === "")
        return true;
    for (let i = 0; i < num.length; i++) {
        if (num.charAt(i) < '0' || num.charAt(i) > '9')
            return false;
    }
    return true;
}

export const isNonNegativeNumber = (num: string): boolean => {
    if (num === "")
        return true;
    if (num.charAt(0) === '0' && num.length !== 1)
        return false;
    for (let i = 0; i < num.length; i++) {
        if (num.charAt(i) < '0' || num.charAt(i) > '9')
            return false;
    }
    return true;
}

export const defaultQuestionList = [
    {
        questionName: "",
        questionType: {
            name: 'Single answer question',
            type: 'SINGLE'
        },
        possibleAnswersDto: [
            {
                possibleAnswer: "",
                answerStatus: "WRONG",
                answerValue: ""
            }
        ]
    }
];

export const questionTypes = [
    { name: 'Single answer question', type: 'SINGLE' },
    { name: 'Multiple answer question', type: 'MULTI' },
    { name: 'Open-ended question', type: 'OPEN' }
];

export const addNewQuestion = (setQuestionList) => {
    setQuestionList(old => [...old, {
        questionName: "",
        questionType: {
            name: 'Single answer question',
            type: 'SINGLE'
        },
        possibleAnswersDto: [
            {
                possibleAnswer: "",
                answerStatus: "WRONG",
                answerValue: ""
            }
        ]
    }])
}

export const deleteQuestion = (questionList, questionIndex, setQuestionList) => {
    setQuestionList(questionList.filter((value, index) => index !== questionIndex))
}

export const setQuestionName = (question, questionName, setQuestionList) => {
    question.questionName = questionName;
    setQuestionList(old => [...old]);
}

export const setQuestionSelectedType = (questionList, index, newType, setQuestionList) => {
    console.log(newType)
    const question = questionList[index];
    question.questionType = newType;
    questionList[index] = question;
    setQuestionList(old => [...old]);
}

export const addAnswer = (question, setQuestionList) => {
    question.possibleAnswersDto.push({
        possibleAnswer: "",
        answerStatus: "WRONG",
        answerValue: ""
    });
    setQuestionList(old => [...old]);
}

export const deleteAnswer = (question, answerDeleteIndex, setQuestionList) => {
    question.possibleAnswersDto = question.possibleAnswersDto.filter((value, index) => index !== answerDeleteIndex);
    setQuestionList(old => [...old]);
}

export const setAnswerGrade = (answer, grade, setQuestionList) => {
    if (grade === "")
        answer.answerValue = "";
    else
        answer.answerValue = parseInt(grade);
    setQuestionList(old => [...old]);
}

export const setAnswerValue = (answer, possibleAnswer, setQuestionList) => {
    answer.possibleAnswer = possibleAnswer;
    setQuestionList(old => [...old]);
}

export const setCorrectOpenAnswerQuestion = (answer, setQuestionList) => {
    answer.answerStatus = "CORRECT";
    setQuestionList(old => [...old]);
}

export const setCorrectSingleAnswerQuestion = (question, correctAnswerIndex, setQuestionList) => {
    question.possibleAnswersDto.forEach(answer => answer.answerStatus = "WRONG");
    question.possibleAnswersDto[correctAnswerIndex].answerStatus = "CORRECT";
    setQuestionList(old => [...old]);
}

export const getCorrectSingleAnswerQuestion = (question): string => {
    const answerList = question.possibleAnswersDto;
    for (let i = 0; i < answerList.length; i++) {
        if (answerList[i].answerStatus === "CORRECT")
            return (i + 1).toString();
    }
    return "";
}

export const isCorrectMultipleAnswerQuestion = (question, answerIndex): boolean => {
    const answerList = question.possibleAnswersDto;
    return answerList[answerIndex].answerStatus === "CORRECT";
}

export const setCorrectMultipleAnswerQuestion = (question, answerIndex, correct: boolean, setQuestionList) => {
    const answerList = question.possibleAnswersDto;
    answerList[answerIndex].answerStatus = (correct) ? "CORRECT" : "WRONG";
    setQuestionList(old => [...old]);
}

export const validateNewFormDto = (newForm): boolean => {
    if (newForm.topicName === "" || newForm.attempts === "" || newForm.expireDateTime === "")
        return false;
    const questions = newForm.questionDtoList;
    for (let i = 0; i < questions.length; i++) {
        const question = questions[i];
        if (question.questionName === "")
            return false;

        const answers = question.possibleAnswersDto;
        for (let j = 0; j < answers.length; j++) {
            const answer = answers[j];
            if (answer.possibleAnswer === "" || answer.answerValue === "")
                return false;
        }
    }
    return true;
}

const creteExpireDate = (plusDays: number): string => {
    const date = new Date();
    date.setDate(date.getDate() + plusDays);
    return date.toISOString();
}

export const createNewFormDto = (formTitle, maxAttempts, expiresInDays, questionList): object => {
    return {
        topicName: formTitle,
        attempts: maxAttempts,
        expireDateTime: creteExpireDate(expiresInDays),
        questionDtoList: questionList
    }
}