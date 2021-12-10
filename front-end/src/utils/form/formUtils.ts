export const defaultForm = {
    topicName: "Algebra test",
    attempts: 0,
    expireDateTime: "",
    questionDtoList: [
        {
            questionName: "2 + 2 is",
            questionType: "SINGLE",
            possibleAnswersDto: [
                {
                    possibleAnswer: "4",
                    answerStatus: "",
                    answerValue: 0
                },
                {
                    possibleAnswer: "5",
                    answerStatus: "",
                    answerValue: 0
                },
                {
                    possibleAnswer: "6",
                    answerStatus: "",
                    answerValue: 0
                }
            ]
        },
        {
            questionName: "PI is between",
            questionType: "MULTI",
            possibleAnswersDto: [
                {
                    possibleAnswer: "2 and 3",
                    answerStatus: "",
                    answerValue: 0
                },
                {
                    possibleAnswer: "3 and 4",
                    answerStatus: "",
                    answerValue: 0
                },
                {
                    possibleAnswer: "2.5 and 3.5",
                    answerStatus: "",
                    answerValue: 0
                }
            ]
        },
        {
            questionName: "Multiply and enter the answer: 25 * 25",
            questionType: "OPEN",
            possibleAnswersDto: [
                {
                    possibleAnswer: "",
                    answerStatus: "",
                    answerValue: 0
                }
            ]
        }
    ]
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

    console.log("FORM: ")
    console.log(form)

    const submitFormDto = {
        formId: formId,
        questionDtoList: [] as any
    }

    const questionList = form.questionDtoList;

    for (let i = 0; i < questionList.length; i++) {
        const question = questionList[i];

        console.log(submitFormDto)

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