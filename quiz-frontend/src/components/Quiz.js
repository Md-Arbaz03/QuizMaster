import { useState } from "react";

const Quiz = ({ questions, onSubmitQuiz }) => {
  const [selectedAnswers, setSelectedAnswers] = useState({});

  const handleAnswerChange = (questionId, answer) => {
    setSelectedAnswers((prev) => ({ ...prev, [questionId]: answer }));
  };

  const handleSubmit = () => {
    const responses = Object.keys(selectedAnswers).map((questionId) => ({
      id: parseInt(questionId),
      response: selectedAnswers[questionId],
    }));

    if (responses.length !== questions.length) {
      alert("Please answer all questions before submitting.");
      return;
    }
    onSubmitQuiz(responses);
  };

  return (
    <div>
      <h2 className="text-center mb-4">Answer the Questions</h2>
      {questions.map((q, index) => (
        <div key={q.id} className="card mb-3">
          <div className="card-header fw-bold">
            {index + 1}. {q.questionTitle}
          </div>
          <div className="card-body">
            <ul className="list-group list-group-flush">
              {[q.option1, q.option2, q.option3, q.option4].map((option, i) => (
                <li key={i} className="list-group-item">
                  <div className="form-check">
                    <input
                      className="form-check-input"
                      type="radio"
                      name={`question-${q.id}`}
                      id={`option-${q.id}-${i}`}
                      value={option}
                      checked={selectedAnswers[q.id] === option}
                      onChange={() => handleAnswerChange(q.id, option)}
                    />
                    <label
                      className="form-check-label w-100"
                      htmlFor={`option-${q.id}-${i}`}
                    >
                      {option}
                    </label>
                  </div>
                </li>
              ))}
            </ul>
          </div>
        </div>
      ))}
      <div className="d-grid mt-4">
        <button onClick={handleSubmit} className="btn btn-success">
          Submit Quiz
        </button>
      </div>
    </div>
  );
};

export default Quiz;
