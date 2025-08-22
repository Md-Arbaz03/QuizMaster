import axios from "axios";

// The base URL for your Spring Boot backend
const API_URL = "http://localhost:8090/quiz";

/**
 * Creates a new quiz and returns its ID.
 * Your backend MUST return the new quiz ID for this to work.
 */
export const createQuiz = async (quizDto) => {
  const response = await axios.post(`${API_URL}/create`, quizDto);
  return response.data; // This should be the new Quiz ID
};

/**
 * Fetches questions for a specific quiz by its ID.
 */
export const getQuizQuestions = async (id) => {
  const response = await axios.get(`${API_URL}/get/${id}`);
  return response.data; // Returns the list of questions
};

/**
 * Submits the user's answers and gets the score.
 */
export const submitQuiz = async (responses) => {
  const response = await axios.post(`${API_URL}/submit`, responses);
  return response.data; // Returns the final score
};

/**
 * Fetches a list of all quizzes.
 */
export const getAllQuizzes = async () => {
  const response = await axios.get(`${API_URL}/all`);
  return response.data;
};
