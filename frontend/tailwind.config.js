/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.vue"
  ],
  theme: {
    extend: {
      fontFamily: {
        'Sriracha': ['Sriracha', 'cursive'],
        'PassionOne': ['Passion One', 'cursive'],
      }
    },
  },
  plugins: [],
} 