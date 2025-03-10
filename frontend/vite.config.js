import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { resolve } from "path";
import eslintPlugin from "vite-plugin-eslint";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue(),
    eslintPlugin()],
  server: {
    proxy: {
      "/api": {
        target: {
          host: "localhost",
          port: 8080,
          protocol: "http",
        },
        changeOrigin: true,
      },
    },
  },
  resolve: {
    extensions: [".mjs", ".js", ".ts", ".jsx", ".tsx", ".json", ".vue"],
    alias: {
      "@": resolve(__dirname, "src"),
    },
  },
})


// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true,
//   devServer: {
//     proxy: {
//       '^/api': {
//         target: 'http://localhost:8080',
//         changeOrigin: true
//       },
//     }
//   }
// })
