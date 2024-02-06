import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

const BASE_API_URL = import.meta.env.VITE_API_URL;
console.log(`BASE_API_URL: ${BASE_API_URL}`);

interface User {
   id: number;
   username: string;
   email: string;
   password: string;
   age: number;
}

export const apiSlice = createApi({
   reducerPath: "api",
   baseQuery: fetchBaseQuery({ baseUrl: BASE_API_URL }),
   endpoints: (builder) => ({
      getAllUsers: builder.query<User[], void>({
         query: () => `api/all-users`,
      }),
   }),
});

export const { useGetAllUsersQuery } = apiSlice;
