# Stage 1: Build the Angular app
FROM node:18-alpine AS build

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . . 
RUN npm run build --prod

# Stage 2: Serve with Nginx
FROM nginx:alpine

# Copy built Angular files to Nginx's HTML directory
COPY --from=build /app/dist/belgische-regering-application/browser/ /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
