/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*", "./resources/public/index.html"],
  darkMode: 'class', // https://tailwindcss.com/docs/dark-mode#supporting-system-preference-and-manual-selection
  theme: {
    extend: {},
  },
  // plugins: [require('@tailwindcss/typography')], // typography seems more for articles
}
