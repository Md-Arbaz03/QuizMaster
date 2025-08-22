import { useState } from "react";

const QuizSetup = ({ onCreateQuiz }) => {
  const [category, setCategory] = useState("Java");
  const [numQ, setNumQ] = useState(5);
  const [title, setTitle] = useState("Java Basics Quiz");

  // This function updates the title automatically when the category changes
  const handleCategoryChange = (e) => {
    const newCategory = e.target.value;
    setCategory(newCategory);
    setTitle(`${newCategory} Basics Quiz`);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onCreateQuiz({ category, numQ, title });
  };

  return (
    <div>
      <h2 className="card-title text-center mb-4">Create Your Quiz</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="title" className="form-label">
            Title
          </label>
          <input
            type="text"
            id="title"
            className="form-control"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="category" className="form-label">
            Category
          </label>
          <select
            id="category"
            className="form-select"
            value={category}
            onChange={handleCategoryChange}
          >
            <option value="Java">Java</option>
            <option value="Python">Python</option>
            <option value="JavaScript">JavaScript</option>
          </select>
        </div>
        <div className="mb-3">
          <label htmlFor="numQ" className="form-label">
            Number of Questions
          </label>
          <input
            type="number"
            id="numQ"
            className="form-control"
            value={numQ}
            onChange={(e) => setNumQ(e.target.value)}
            min="1"
            max="10"
            required
          />
        </div>
        <div className="d-grid">
          <button type="submit" className="btn btn-primary">
            Start Quiz
          </button>
        </div>
      </form>
    </div>
  );
};

export default QuizSetup;
