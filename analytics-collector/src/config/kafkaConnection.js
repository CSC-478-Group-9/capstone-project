module.exports = {
    clientId: 'nodeJS-analytics-collector',
    kafka_topic: 'sessions-01',
    brokers: ['0.0.0.0:9092'],
    connectionTimeout: 3000,
    authenticationTimeout: 1000,
    reauthenticationThreshold: 10000,
};