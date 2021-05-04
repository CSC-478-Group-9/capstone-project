  
FROM node:14-alpine as build

WORKDIR /usr/src/analytics-collector/

COPY package*.json /usr/src/analytics-collector/

RUN  npm ci

COPY . .

FROM node:14-alpine as dev

WORKDIR /usr/src/analytics-collector/

COPY --from=build /usr/src/analytics-collector/node_modules node_modules

USER node

CMD npm run dev