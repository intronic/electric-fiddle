/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*",
    "./src-build/**/*",
    "./src-dev/**/*",
    "./src-fiddles/**/*",
    "./src-prod/**/*",
    "./resources/public/index.html"
  ],
  darkMode: 'class', // https://tailwindcss.com/docs/dark-mode#supporting-system-preference-and-manual-selection
  theme: {
    extend: {},
  },
  plugins: [
    require('@tailwindcss/forms'),
    // require('@tailwindcss/typography')  // typography seems more for articles, eg differently formatted text inside <article> sections
  ],
}
