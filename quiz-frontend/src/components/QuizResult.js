const QuizResult = ({ score, total, onTryAgain }) => {
  return (
    <div className="text-center">
      <h2 className="text-success">Quiz Complete!</h2>
      <p className="fs-5 mt-3">Your Final Score:</p>
      <h1 className="display-3 fw-bold text-primary">
        {score} / {total}
      </h1>
      <button onClick={onTryAgain} className="btn btn-secondary mt-4">
        Take Another Quiz
      </button>
    </div>
  );
};

export default QuizResult;
