import axios from 'axios';

const API_URL = "http://localhost:8080/api";

// Set default URL globally
axios.defaults.baseURL = API_URL;
// Set default headers globally
axios.defaults.headers['Content-Type'] = 'application/json';

export default axios;