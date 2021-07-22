drop table if exists employee;
create table employee(
employeeID varchar(6) not null primary key comment'社員ID',
employeeName varchar(12) not null comment'社員氏名',
password varchar(50) not null comment'パスワード',
status varchar(1) not null comment'ステータス',
sex varchar(1) comment'性別',
epType varchar(1) not null comment'タイプ',
birthday varchar(8) comment'生年月日',
age varchar(2) comment'年齢',
joinedDate varchar(8) comment'入社年月日',
joinedTime varchar(2) comment'社齢',
postCode varchar(7) comment'郵便番号',
address varchar(200) comment'住所',
phoneNumber varchar(15) comment'電話番号',
authority varchar(1) comment'権限',
mailAdress varchar(40) not null comment'メール',
insertDate varchar(8) comment'作成日',
updateDate varchar(8) comment'更新日')comment'社員情報';
Insert into employee values
('E001' ,'社員１' ,md5('123456') ,'0' ,'0','0','19860101' ,'34','20190101','2','2310859','横浜市中区','07012344321','0', 'e001@it-softtech.com',date_format(now(),'%Y%m%d') ,null),
('E002' ,'社員２' ,md5('123456') ,'0' ,'0' ,'0','19860102' ,'34','20190101','2','2310859','横浜市中区','07012344322','0', 'e002@it-softtech.com',date_format(now(),'%Y%m%d') ,null),
('E003' ,'社員３' ,md5('123456') ,'0' ,'0' ,'0','19860103' ,'34','20190101','2','2310859','横浜市中区','07012344323','1','e003@it-softtech.com',date_format(now(),'%Y%m%d') ,null);

drop table if exists ofcfunction;
create table ofcfunction(
functionID varchar(2) not null primary key comment'機能ID',
functionName varchar(12) not null comment'機能名称',
functionText varchar(200) not null comment'機能表示名称',
authority varchar(1) not null comment'権限',
functionLink varchar(50) not null comment'機能URL',
displayNo varchar(2) comment'表示順',
deleteFlg varchar(1) not null comment'削除フラグ',
insertDate varchar(8) comment'作成日',
updateDate varchar(8) comment'更新日'
) comment'機能情報';


Insert into ofcfunction values
('S1','employee','&#xe666;&emsp;社員情報管理','1','/employee','0' ,'0',date_format(now(),'%Y%m%d') ,null),
('S2','company','&#xe65c;&emsp;取引先情報管理','1','/company','1' ,'0',date_format(now(),'%Y%m%d') ,null),
('S3','contract','&#xe65d;&emsp;契約情報管理','1','/contract','2' ,'1',date_format(now(),'%Y%m%d') ,null),
('S4','workInfo','&#xe672;&emsp;勤怠情報管理','1','/workInfo','3' ,'1',date_format(now(),'%Y%m%d') ,null),
('S5','claim','&#xe681;&emsp;請求情報管理','1','/claim','4' ,'1',date_format(now(),'%Y%m%d') ,null),
('S6','transport','&#xe612;&emsp;交通情報管理','1','/transport','6' ,'1',date_format(now(),'%Y%m%d') ,null),
('S7','salaryInfo','&#xe60c;&emsp;給料情報管理','1','/salaryInfo','7' ,'1',date_format(now(),'%Y%m%d') ,null),
('A7','workdetail','&#xe672;&emsp;勤怠情報','0','/workdetail','1' ,'0',date_format(now(),'%Y%m%d') ,null),
('A8','salarydetail','&#xe60c;&emsp;給料明細','0','/salarydetail','0' ,'0',date_format(now(),'%Y%m%d') ,null),
('A9','password','&#xe696;&emsp;パスワード変更','0','/passwd','99' ,'0',date_format(now(),'%Y%m%d') ,null),
('A1','salarylist','&#xe60c;&emsp;給料リスト','1','/emsm/salarylist','4' ,'0',date_format(now(),'%Y%m%d') ,null),
('A4','welfareList','&#xe681;&emsp;福祉控除リスト','1','/emsm/welfarelist','0' ,'0',date_format(now(),'%Y%m%d') ,null),
('A6','workdetailli','&#xe60c;&emsp;勤怠リスト','1','/emsm/workdetaillist','3' ,'0',date_format(now(),'%Y%m%d') ,null);
alter table ofcfunction add column sysType varchar(1);

drop table if exists company;
create table company(
companyID varchar(6) not null primary key comment'取引先ID',
companyName varchar(50) not null comment'取引先名称',
companyType varchar(1) comment'取引先種類',
postCode varchar(7) not null comment'郵便番号',
address varchar(100) not null comment'住所',
basicContractDate varchar(8) comment'基本契約日',
phoneNumber varchar(15) not null comment'電話番号',
contactName varchar(20) comment'連絡先名',
mail varchar(20) comment'メール',
contractStatus varchar(1) comment'ステータス',
level varchar(1) comment'評判レベル',
insertDate varchar(8) comment'作成日',
updateDate varchar(8) comment'更新日')comment'取引先情報';
Insert into company values
('C001','木の葉隠れの里株式会社','0','2310859','横浜市西区','20200101','07012340000','火影綱手', 'aaa@gmail.com','0','0',date_format(now(), '%Y%m%d'), null),
('C002','霧隠れの里株式会社','0','2310859','横浜市西区','20200101','07012340001','水影照美冥', 'bbb@gmail.com','0','0',date_format(now(), '%Y%m%d'), null),
('C003','岩隠れの里株式会社','0','2310859','横浜市西区','20200101','07012340001','土影大野木', 'ccc@gmail.com','0','0',date_format(now(), '%Y%m%d'), null);

drop table if exists contract;
create table contract(
contractID varchar(10) not null primary key comment'契約ID',
contractName varchar(50) not null comment'契約名称',
employeeID varchar(6) not null comment'社員ID',
companyID varchar(6) not null comment'取引先ID',
price int(7) not null comment'単価',
payOff varchar(1) not null comment'精算タイプ',
lowerTime varchar(3) not null comment'契約下限',
lowerPrice int(6) not null comment'控除単価',
upperTime varchar(3) not null comment'契約上限',
upperPrice int(6) not null comment'残業単価',
contractBeginDate varchar(8)  not null comment'契約開始日',
contractEndDate varchar(8) comment'契約終了日',
paymentTerm varchar(2) not null comment'支払サイト',
postNeed varchar(1) comment'原本郵送フラグ',
timeReportPath varchar(50) not null comment'タイムレポートパス',
invoice varchar(50) comment'請求書名称',
status varchar(1) not null comment'進行ステータス',
insertDate varchar(8) comment'作成日',
updateDate varchar(8) comment'更新日'
)comment'契約情報';

Insert into contract values
('CT001','火遁忍術開発支援','E001','C001',600000,'0',150,1000,200,1000,'20210101','20210131','10','0','D:/tmp/work',' 火遁忍術開発支援', '1',date_format(now(), '%Y%m%d'), date_format(now(), '%Y%m%d')),
('CT002','水遁忍術開発支援','E002','C002',600000,'0',150,1000,200,1000,'20210101','20210131','10','0','D:/tmp/work',' 水遁忍術開発支援', '1',date_format(now(), '%Y%m%d'), date_format(now(), '%Y%m%d')),
('CT003','水遁忍術開発支援','E003','C003',600000,'0',150,1000,200,1000,'20210101','20210131','10','0','D:/tmp/work',' 土遁忍術開発支援', '1',date_format(now(), '%Y%m%d'), date_format(now(), '%Y%m%d'));

drop table if exists workinfo;
create table workinfo(
workInfoID varchar(10) not null  comment'稼働情報ID',
contractID varchar(10) not null  comment'契約ID',
workMonth varchar(6) not null comment'稼働月',
workStartDay varchar(8)	not null comment'稼働開始日',
workEndDay varchar(8) not null comment'稼働最終日',
workTime int(3) not null comment'稼働時間',
workInfoFile varchar(50) comment'稼働表パス',
insertDate varchar(8) comment'作成日',
updateDate varchar(8) comment'更新日',
primary key(workInfoID,contractID,workMonth)
)comment'勤怠情報';
Insert into workinfo values
('W001','CT001','202101','20210101','20210131',180,'D:\\Sheet\\', date_format(now(), '%Y%m%d'), null),
('W002','CT002','202101','20210101','20210131',180,'D:\\Sheet\\', date_format(now(), '%Y%m%d'), null),
('W003','CT003','202101','20210101','20210131',180,'D:\\Sheet\\', date_format(now(), '%Y%m%d'), null);
alter table workinfo modify workTime float not null default 0 comment'稼働時間';

drop table if exists claim;
create table claim(
claimID varchar(10) not null primary key comment'請求ID',
contractID varchar(10) not null comment'契約ID',
claimMonth varchar(8) not null comment'請求月',
workTime int(3) comment'稼働時間',
exceTime int(3) comment'過稼働時間',
addpayOff int(7) comment'加算額',
deficiTime int(3) comment'不足稼働時間',
minusPayOff int(7) comment'減算額',
transport int(7) comment'交通費',
businessTrip int(7) comment'出張旅費',
taxRate int(2) comment'消費税率',
consumpTax int(7) comment'消費税',
sum int(8) comment'合計',
specialClaim int(8) comment'特別請求',
claimStatus varchar(1) not null comment'請求ステータス',
insertDate varchar(8) comment'作成日',
updateDate varchar(8) comment'更新日'
)comment'請求情報';

drop table if exists salaryinfo;
create table salaryinfo(
employeeID varchar(6) not null  comment'社員ID',
month  varchar(6) not null  comment'対象月',
paymentDate  varchar(8) not null comment'支払日',
base int(8) not null comment'基本給',
overTime int(8) comment'残業時間',
shortage int(8) comment'不足時間',
overTimePlus int(8) comment'残業加算',
shortageReduce int(8) comment'稼働不足減',
transportExpense int(6) comment'交通費',
allowancePlus int(6) comment'手当加算',
allowanceReduce int(6) comment'手当減算',
allowanceReason  varchar(50) comment'手当理由',
welfareSelf int(6) comment'厚生控除個人',
welfareComp int(6) comment'厚生控除会社',
welfareBaby int(6) comment'厚生控除子育(会社)',
eplyInsSelf int(6) comment'雇用保険個人負担',
eplyInsComp int(6) comment'雇用保険会社負担',
eplyInsWithdraw int(6) comment'雇用保拠出金（会社)',
wkAcccpsIns int(6) comment'労災保険（会社負担のみ）',
withholdingTax int(6) comment'源泉控除',
municipalTax int(6) comment'住民税控除',
rental int(6) comment'社宅家賃控除',
rentalMgmtFee int(6) comment'社宅共益費控除',
sum int(9) not null comment'総額',
totalFee int(9) not null comment'総費用',
remark varchar(200) comment'備考',
deleteFlg  varchar(1) comment'削除フラグ',
insertDate  varchar(8) comment'作成日',
updateDate  varchar(8) comment'更新日',
primary key(employeeID,month)
)comment'給料情報';
Insert into salaryinfo (employeeID,month,paymentDate,base,overTime,shortage,overTimePlus,shortageReduce,transportExpense,allowancePlus,allowanceReduce,allowanceReason,welfareSelf,welfareComp,welfareBaby,eplyInsSelf,eplyInsComp,eplyInsWithdraw,wkAcccpsIns,withholdingTax,municipalTax,rental,rentalMgmtFee,sum,totalFee,remark,deleteFlg,insertDate,updateDate)
values ('E001','202001','20200215',60000,0,0,0,0,0,0,0,'',0,0,0,0,0,0,0,0,0,0,0,60000,60000,'','0','20210122','20210122');

alter table ems.salaryinfo CHANGE welfareSelf welfarePensionSelf int DEFAULT 0 COMMENT '厚生年金控除個人';
alter table ems.salaryinfo add column welfarePensionComp int DEFAULT 0 COMMENT '厚生年金控除会社' after welfarePensionSelf;
alter table ems.salaryinfo CHANGE welfareComp welfareHealthComp int DEFAULT 0 COMMENT '厚生健康控除会社';
alter table ems.salaryinfo add column welfareHealthSelf int DEFAULT 0 COMMENT '厚生健康控除個人' after welfarePensionSelf;

drop table if exists transport;
create table transport(
employeeID varchar(6) not null comment'社員ID',
workMonth varchar(6) not null comment'対象月',
startDate varchar(8) not null comment'開始日',
startStation varchar(20) not null comment'起点駅',
endStation varchar(20) not null comment'終点駅',
transportFacility varchar(20) not null comment'交通機関(代表)',
transportExpense1 float(6) not null comment'定期券金額(1ヶ月)',
midStation1 varchar(20) comment'中間駅1',
transportFacility1 varchar(20) comment'交通機関1',
midStation2 varchar(20) comment'中間駅2',
transportFacility2 varchar(20) comment'交通機関2',
midStation3 varchar(20) comment'中間駅3',
transportFacility3 varchar(20) comment'交通機関3',
transportExpense2 float(6) comment'定期券金額(2ヶ月)',
transportExpense3 float(6) comment'定期券金額(3ヶ月)',
transport int(7) comment'交通費',
businessTrip int(7) comment'出張旅費',
BusinessTripName varchar(10) comment'出張旅費ファイル',
status varchar(1) not null comment'使用ステータス',
insertDate varchar(8) comment'作成日',
updateDate varchar(8) comment'更新日',
primary key(employeeID,workMonth)
) comment'交通情報';
insert into transport values
("E001","202104","20210101","西川口駅","銀座駅","京浜東北線",3000,"赤羽駅","埼京線","新宿駅","埼京線","新宿駅","丸の内線",0,0,10000,2500,'D:\\TName\\',"0",date_format(now(), '%Y%m%d'), null);

drop table if exists welfareinfo;
create table welfareinfo(
employeeID varchar(6) not null comment'社員ID',
startDate varchar(8) not null comment'控除開始日',
base int(8) not null comment'基本給',
welfarePensionSelf int(6) comment'厚生年金控除個人',
welfarePensionComp int(6) comment'厚生年金控除会社',
welfareHealthComp int(6) comment'厚生健康控除会社',
welfareHealthSelf int(6) comment'厚生健康控除個人',
welfareBaby int(6) comment'厚生控除子育(会社)',
eplyInsSelf int(6) comment'雇用保険個人負担',
eplyInsComp int(6) comment'雇用保険会社負担',
eplyInsWithdraw int(6) comment'雇用保拠出金（会社)',
wkAcccpsIns int(6) comment'労災保険（会社負担のみ）',
withholdingTax int(6) comment'源泉控除',
municipalTax int(6) comment'住民税控除',
rental int(6) comment'社宅家賃控除',
rentalMgmtFee int(6) comment'社宅管理費控除',
status varchar(1) not null comment'控除ステータス',
insertDate varchar(8) comment'作成日',
insertEmployee varchar(6) comment'作成者',
updateDate varchar(8) comment'更新日',
updateEmployee varchar(6) comment'更新者',
primary key(employeeID,startDate)
)comment'福祉情報';


