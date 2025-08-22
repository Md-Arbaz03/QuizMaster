import { useState, useEffect } from "react";
import { getAllQuizzes } from "../services/api";

const QuizList = ({ onStartQuiz, onShowSetup }) => {
  const [quizzes, setQuizzes] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchQuizzes = async () => {
      try {
        const data = await getAllQuizzes();
        setQuizzes(data);
      } catch (err) {
        setError("Failed to fetch quizzes. Please try again later.");
        console.error(err);
      }
    };
    fetchQuizzes();
  }, []);

  return (
    <div className="text-center">
      <h2 className="mb-4">Choose a Quiz</h2>
      {error && <div className="alert alert-danger">{error}</div>}
      <div className="list-group mb-4">
        {quizzes.length > 0 ? (
          quizzes.map((quiz) => (
            <button
              key={quiz.id}
              onClick={() => onStartQuiz(quiz.id)}
              className="list-group-item list-group-item-action"
            >
              {quiz.title} (ID: {quiz.id})
            </button>
          ))
        ) : (
          <p>No past quizzes found.</p>
        )}
      </div>
      <button onClick={onShowSetup} className="btn btn-primary">
        Or, Create a New Quiz
      </button>
    </div>
  );
};

export default QuizList;
