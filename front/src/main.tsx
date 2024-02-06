import React from "react";
import ReactDOM from "react-dom/client";
import App from "./components/App.tsx";
import "./index.css";
import { ApiProvider } from "@reduxjs/toolkit/query/react";
import { apiSlice } from "./api/apiSlice.ts";

ReactDOM.createRoot(document.getElementById("root")!).render(
   <React.StrictMode>
        <ApiProvider api={apiSlice}>
            <App />
        </ApiProvider>
   </React.StrictMode>
);
