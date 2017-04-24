
select T_TimeStamp_15to16, amount_15to16, T_TimeStamp_16to17, amount_16to17, T_TimeStamp_17to18, amount_17to18 FROM

(SELECT date_format(Barclays.T_TimeStamp, '%H:00 hrs') as T_TimeStamp_15to16,sum(Amount) as amount_15to16 from Barclays WHERE T_TimeStamp between '2017-03-09 15:00:00' and '2017-03-09 15:59:00') data_15to16,

(SELECT date_format(Barclays.T_TimeStamp, '%H:00 hrs') as T_TimeStamp_16to17,sum(Amount) as amount_16to17 from Barclays WHERE T_TimeStamp between '2017-03-09 16:00:00' and '2017-03-09 16:59:00') data_16to17,

(SELECT date_format(Barclays.T_TimeStamp, '%H:00 hrs') as T_TimeStamp_17to18,sum(Amount) as amount_17to18 from Barclays WHERE T_TimeStamp between '2017-03-09 17:00:00' and '2017-03-09 17:59:00') data_17to18;
