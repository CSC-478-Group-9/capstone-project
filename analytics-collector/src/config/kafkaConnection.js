module.exports = {
    clientId: 'lkc-xzkoz',
    kafka_topic: 'sessions-01',
    brokers: ['pkc-lzvrd.us-west4.gcp.confluent.cloud:9092'],
    ssl: true,
    sasl: {
        mechanism: 'PLAIN', // scram-sha-256 or scram-sha-512
        username: 'A72LHIJOL4YD2IJI',
        password: '7UXVLGNJkfJXTnozbn8d4x66BpHu6FuixEUN7Wj+fSEm0YmyJ1BGRG+k5cfP/Rxn'
    },
    connectionTimeout: 3000,
    authenticationTimeout: 1000,
    reauthenticationThreshold: 10000,
};