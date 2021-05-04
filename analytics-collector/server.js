'use strict';

const express = require('express');
const {Kafka} = require('kafkajs');
const kafkaConfig = require('./src/config/kafkaConnection');
const bodyParser = require("body-parser");
const cors = require('cors');
const {checkSchema, validationResult} = require('express-validator');

// Constants
const PORT = 4100;
const kafka = new Kafka(kafkaConfig);
const producer = kafka.producer();

const mysql = require('mysql');
const connection = mysql.createConnection('mysql://tracker:Qn@lyt1c5D@$b0@4d@165.232.159.45/analytics_dashboard');

const HOST = '0.0.0.0';
const app = express();
app.use(bodyParser.json());

app.options('/v1/track', cors());

app.post('/v1/track',
    checkSchema({
        type: {
            in: ['body'],
            exists: {
                errorMessage: 'tracking event missing type'
            }
        },
        anonymousID: {
            in: ['body'],
            exists: {
                errorMessage: 'tracking event missing anonymousID'
            }
        },
        language: {
            in: ['body'],
            exists: {
                errorMessage: 'tracking event missing language'
            }
        },
        platform: {
            in: ['body'],
            exists: {
                errorMessage: 'tracking event missing platform'
            }
        },
        port: {
            in: ['body'],
            exists: {
                errorMessage: 'tracking event missing port'
            }
        },
        referer: {
            in: ['body'],
            exists: {
                errorMessage: 'tracking event missing referer'
            }
        },
        location: {
            in: ['body'],
            exists: {
                errorMessage: 'tracking event missing location'
            }
        },
        href: {
            in: ['body'],
            exists: {
                errorMessage: 'tracking event missing href'
            }
        },
        origin: {
            in: ['body'],
            exists: {
                errorMessage: 'tracking event missing origin'
            }
        },
        title: {
            in: ['body'],
            exists: {
                errorMessage: 'tracking event missing title'
            }
        },
        endpoint: {
            in: ['body'],
            exists: {
                errorMessage: 'tracking event missing endpoint'
            }
        }
    }), (req, res) => {
        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            return res.status(400).json({errors: errors.array()});
        }

        connection.query('SELECT 1 FROM assets WHERE api_token = ?', [req.body.apiKey], async (error, row) => {
            if (error) throw error;
            if (row.length > 0) {
                const message = req.body

                try {
                    await producer.connect()
                } catch (e) {
                    res.sendStatus(503);
                }

                try {
                    await producer.send({
                        topic: 'sessions-01',
                        messages:
                            [{key: message.anonymousID, value: JSON.stringify(message)}],
                    });
                    res.status(200).json({status: 'message sent'});
                } catch (e) {
                    res.status(500).json({[e.message]: e});
                }
            } else {
                res.status(401).json({"error": "Bad api key"});
            }
        });
    });

app.listen(PORT, HOST);
console.log(`Running on http://${HOST}:${PORT}`);