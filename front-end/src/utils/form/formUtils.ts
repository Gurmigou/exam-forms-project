export const defaultForm = {
    topicName: "",
    attempts: 0,
    expireDateTime: "",
    questionDtoList: []
}

export const setSingleAnswer = (answerList, answerIndex, setRender) => {
    for (let i = 0; i < answerList.length; i++) {
        answerList[i].answerStatus = "";
    }
    answerList[answerIndex].answerStatus = "CORRECT";
    setRender(old => !old)
}

export const setMultiAnswer = (answerList, answerIndex, value, setRender) => {
    answerList[answerIndex].answerStatus = value ? "CORRECT" : "";
    setRender(old => !old);
}

export const setOpenAnswer = (answer, value, setRender) => {
    answer.possibleAnswer = value;
    setRender(old => !old);
}

export const validateUserAnswersForm = (form): boolean => {
    const questionList = form.questionDtoList;
    for (let i = 0; i < questionList.length; i++) {
        const question = questionList[i];
        const answerList = questionList[i].possibleAnswersDto;

        if (question.questionType === "SINGLE" || question.questionType === "MULTI") {
            let atLeastOneAnswer: boolean = false;

            for (let j = 0; j < answerList.length; j++) {
                const answer = answerList[j];

                if (answer.answerStatus === "CORRECT") {
                    atLeastOneAnswer = true;
                    break;
                }
            }

            if (!atLeastOneAnswer)
                return false;

        }
        else {
            if (answerList[0].possibleAnswer === "")
                return false;
        }
    }
    return true;
}

export const createSubmitFormDto = (formId: number, form) => {

    const submitFormDto = {
        formId: formId,
        questionDtoList: [] as any
    }

    const questionList = form.questionDtoList;

    for (let i = 0; i < questionList.length; i++) {
        const question = questionList[i];


        if (question.questionType === "OPEN")
            submitFormDto.questionDtoList.push(question)
        else {
            const answerList = question.possibleAnswersDto;

            const submitQuestion = {
                questionName: question.questionName,
                questionType: question.questionType,
                possibleAnswersDto: [] as any
            };

            for (let j = 0; j < answerList.length; j++) {
                const answer = answerList[j];

                if (answer.answerStatus === "CORRECT")
                    submitQuestion.possibleAnswersDto.push(answer);
            }

            submitFormDto.questionDtoList.push(submitQuestion);
        }
    }

    return submitFormDto;
}