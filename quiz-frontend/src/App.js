import { useState } from "react";
import QuizList from "./components/QuizList";
import QuizSetup from "./components/QuizSetup";
import Quiz from "./components/Quiz";
import QuizResult from "./components/QuizResult";
import { createQuiz, getQuizQuestions, submitQuiz } from "./services/api";

function App() {
  const [quizState, setQuizState] = useState("list");
  const [questions, setQuestions] = useState([]);
  const [score, setScore] = useState(0);

  const handleCreateQuiz = async (quizDto) => {
    try {
      const quizId = await createQuiz(quizDto); // This receives the new ID from the backend
      if (quizId) {
        const questionsData = await getQuizQuestions(quizId);
        setQuestions(questionsData);
        setQuizState("in-progress");
      }
    } catch (error) {
      console.error("Error creating or fetching quiz:", error);
      alert(
        "Failed to create quiz. Please check if the backend is running and the category exists."
      );
    }
  };

  const handleStartQuiz = async (id) => {
    try {
      const questionsData = await getQuizQuestions(id);
      if (questionsData.length === 0) {
        alert(
          "This quiz has no questions. It may have been created with a category that has no questions."
        );
        setQuizState("list");
        return;
      }
      setQuestions(questionsData);
      setQuizState("in-progress");
    } catch (error) {
      console.error("Error starting quiz:", error);
      alert("Failed to fetch questions for this quiz.");
    }
  };

  const handleSubmitQuiz = async (responses) => {
    try {
      const finalScore = await submitQuiz(responses);
      setScore(finalScore);
      setQuizState("finished");
    } catch (error) {
      console.error("Error submitting quiz:", error);
      alert("There was an error submitting your answers.");
    }
  };

  const handleReset = () => {
    setQuizState("list");
    setQuestions([]);
    setScore(0);
  };

  const showSetup = () => {
    setQuizState("setup");
  };

  return (
    <div className="container mt-5">
      <div className="p-4 mb-4 bg-light rounded-3 text-center">
        <h1 className="display-5 fw-bold">QuizMaster</h1>
      </div>
      <div className="card">
        <div className="card-body p-4">
          {quizState === "list" && (
            <QuizList onStartQuiz={handleStartQuiz} onShowSetup={showSetup} />
          )}
          {quizState === "setup" && (
            <QuizSetup onCreateQuiz={handleCreateQuiz} />
          )}
          {quizState === "in-progress" && (
            <Quiz questions={questions} onSubmitQuiz={handleSubmitQuiz} />
          )}
          {quizState === "finished" && (
            <QuizResult
              score={score}
              total={questions.length}
              onTryAgain={handleReset}
            />
          )}
        </div>
      </div>
    </div>
  );
}

export default App;
