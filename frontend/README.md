# spotify-recommender – frontend

Frontend application powered by React. It provides a UI and manages HTTP calls to the backend.

## Getting Started

### Installing Packages

The build tool we use for this project is npm. Run the following command to install all required packages:

```sh
npm install
```

The application is tested with Node.js 14 (LTS) but the CI pipeline also does builds with Node.js 10 and 12.

### Running the Application

After installing packages, you can start the React app using the following command:

```sh
npm start
```

This will start a development server running on localhost:3000.

You can create a productive build using the `build` goal. It requires a web server like Apache or Nginx to serve the outputted files.
