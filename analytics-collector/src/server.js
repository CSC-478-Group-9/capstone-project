'use strict';

const express = require('express');
const {Kafka} = require('kafkajs');
const config = require('./config/kafkaConnection');
const bodyParser = require("body-parser");

// Constants
const PORT = 3000;
const HOST = '0.0.0.0';

const app = express();
app.use(bodyParser.json());

app.post('/v1/track', async (req, res) => {
    const message = req.body;
    const kafka = new Kafka(config);
    const producer = kafka.producer();

    await producer.connect()
    await producer.send({
        topic: 'sessions-01',
        messages:
            [{key: message.anonymousID, value: JSON.stringify(message)}],
    })
    res.json({status: "message sent"})
});

app.listen(PORT, HOST);
console.log(`Running on http://${HOST}:${PORT}`);