# Blockchain and Cryptocurrency in Java

> Blockchain is a peer-to-peer decentralized distributed ledger technology that makes the records of any digital asset
> transparent and unchangeable and works without involving any third-party intermediary.

In simpler words, the blockchain is an **immutable** (unchangeable, meaning a transaction or file recorded cannot be
changed) distributed digital **ledger** (digital record of transactions or data stored in multiple places on a computer
network).

An **asset** can be tangible (a house, car, cash, land) or intangible (intellectual property, patents, copyrights,
branding). Virtually anything of value can be tracked and traded on a blockchain network, reducing risk and cutting
costs for all involved.

> A cryptocurrency is a digital currency designed to work as a medium of exchange through a computer network that is
> not reliant on any central authority, such as a government or bank, to uphold or maintain it.
> It is a decentralized system for verifying that the parties to a transaction have the money they claim to have,
> eliminating the need for traditional intermediaries, such as banks, when funds are being transferred between
> two entities.

In simpler words, cryptocurrency is a digital or virtual currency that is secured by **cryptography**, which makes it
nearly impossible to counterfeit or double-spend. Many cryptocurrencies are decentralized networks based on blockchain
technology — a distributed ledger enforced by a disparate network of computers.

## Table of contents

1. Brief History of Currency
    - Barter System
    - Gold
    - Paper Money
    - Gold Standard
    - Bretton Woods
2. Forex Market
    - Make Money
    - Currency Pairs
    - Major Pairs & Cross Pairs
    - Price, Pips & Lots
    - Leverage
    - Short Selling
    - Market Structure
3. Blockchain & Cryptocurrency - The Big Picture
    - Blockchain Demystified
    - How does Blockchain work
    - Bitcoin example
    - Key elements of a Blockchain
    - Disadvantages of a Blockchain
4. IntelliJ Project Setup
5. Hash Function
6. The Ledger
    - Traditional centralized ledger
    - Public keys, Secret Keys and Digital Signature
    - Decentralized and Distributed ledger
7. Elliptic Curve Cryptography
8. Mining
    - Consensus Protocol
    - Byzantine Generals Problem
    - Proof-Of-Work (PoW)
    - Proof-Of-Stake (PoS)
9. Mempools
10. Merkle-Tree
11. UTXOs & Wallets
12. Block verifications
13. Fork
14. Full Cryptocurrency implementation

### Youtube

![Blockchain and Cryptocurrency in Java playlist](BlockchainAndCryptocurrencyThumbnail.png)

[Blockchain and Cryptocurrency in Java playlist](https://www.youtube.com/playlist?list=PLQDzPczdXrTjU7O6HAKDgm_mLvlDqFl3Y)

---

### Chapter 01 - Brief History of Currency

#### Barter System

Started as early as 6000 BC, Barter is the trade of goods or services between two or more people that does not include
the use of money or a monetary device such as a credit card. Trading is defined as one party providing one good or
service in exchange for another party providing a different good or service. A simple example of a barter relationship
is a plumber who builds a fence for a farmer working 10 hours per month. Instead of paying the plumber $1,000 in cash
for labor and supplies, the farmer may reimburse the plumber with 2 litres of milk per day.

![Barter System](Barter.PNG)

Drawbacks of Barter System:

- need to find people what we actually want: an individual looking for wood will be unable to find a provider of timber
  who is needing something the timber searcher can give.
- to ensure fair trades: how can one compute, for instance, a fair swapping scale of eggs for a TV?

#### Gold

Need for a common unit of exchange:

- be used as a medium of exchange
- store value
- be a unit of value

Gold is a good unit of exchange because it is scarce, hard to harvest or mine and durable. Thus, around 700 BC -  
Gold was started to be used as common unit of exchange.

Drawbacks of using Gold:

- issues with having multiple denominations
- can be debased
- can fluctuate based on supply: like [California Gold Rush](https://en.wikipedia.org/wiki/California_Gold_Rush)
- limited supply and hard to transport

#### Paper Bills

Paper bills = 618-907 BC, Gold Standard = 1879, Bretton Woods = 1944, End of Gold Standard = 1971



---

### Chapter 03 - Blockchain - The Big Picture

#### Blockchain Demystified

Suppose I want to buy a computer from one of my colleague in my office. The colleague wants to sell it as she is moving
to other location. So, to make the transaction (buying and selling) - normal procedure is to have a centralized third
party system (like banks) where the transaction can be recorded, i.e. money transferred from my account to her account.
This transaction will be recorded as bank statements in my account and her credited bank account.

Imagine a world where I can send money directly to my colleague without a bank – in seconds instead of days, and I don’t
pay any exorbitant bank fees.

Or one where I store money in an online wallet not tied to a bank, meaning I am my own bank and have complete control
over my money. I don’t need a bank’s permission to access or move it, and never have to worry about a third party taking
it away, or a government’s economic policy manipulating it.

This is not a world of the future; it is a world that an avid but growing number of early adopters live in right now.
And these are just a few of the important blockchain technology use cases that are transforming the way we trust and
exchange value.

Blockchain technology wants to get rid of this centralized systems and make the whole transaction decentralized and
distributed. A decentralized network offers multiple benefits over the traditional centralized network, including
increased system reliability and privacy. Moreover, such networks are much easier to scale and deal with no real single
point of failure. The reason why Blockchain is distributed is because of shared communication and distributed
processing.

![Blockchain Decentralized](Blockchain1.PNG)

So me and my colleague can do the transaction in the blockchain network of computer buyers and sellers. Blockchain is
a **trust-less** system without any third party => the blockchain itself guarantees trust.

Immutable and distributed are two fundamental blockchain properties. The immutability of the ledger means we can always
trust it to be accurate. Being distributed protects the blockchain from network attacks.

#### How does Blockchain work

![Blockchain Properties](Blockchain2.PNG)

In simpler words, a Blockchain is a continuous growing list of records called **blocks** that are linked and secured
using cryptography.

Blockchain is the underlying technology and data structure of cryptocurrencies where we store transactions in the block.

Each transaction or record on the ledger is stored in a **“block”**. For example, blocks on the Bitcoin blockchain
consist of an average of more than 500 Bitcoin transactions.

The information contained in a block is dependent on and linked to the information in a previous block and, over time,
forms a chain of transactions. Hence, the word **blockchain**.

#### Bitcoin example

Here’s how this process works with **Bitcoin**. When sending Bitcoin, we pay a small fee (in bitcoin) for a network of
computers to confirm our transaction is valid. Our transaction is then bundled with other transactions pending in a
queue to be added to a new block.

The computers (nodes) then work to validate this list of transactions in the block by solving a complex mathematical
problem to come up with a **hash**, which is a 64-digit hexadecimal number.

Once solved, the block is added to the network—and our fee, combined with all other transaction fees in that block, is
the miner’s reward.

Each new block added to the network is assigned a unique key (via cryptography). To obtain each new key, the previous
block’s key and information are inputted into a formula.

As new blocks are continually added through the ongoing **mining** process, they become increasingly secure and harder
to tamper with. Anyone caught trying to edit a record will simply be **ignored**. All future blocks then depend on
information from prior blocks — and this dependency from one block to the next forms a secure chain: the blockchain.

We can see this depicted for house records stored on the blockchain. For example, Block 2 provides a key after taking
all the information from Block 1 into account (including the key) and inputting it into a formula. Block 3, in turn,
provides a new key after taking all the information from Block 1 and Block 2 into account (including the key) and
inputting it into a formula. And so, the process repeats itself indefinitely.

Similar example is described below:

![Blockchain explained](BlockchainExplained.PNG)

#### Key elements of a Blockchain

1. Distributed ledger technology

All network participants have access to the distributed ledger and its immutable record of transactions. With this
shared ledger, transactions are recorded only once, eliminating the duplication of effort that’s typical of traditional
business networks.

2. Immutable records

Records on a blockchain cannot be changed or tampered with. A new block of transactions is only added after a complex
mathematical problem is solved and verified by a consensus mechanism. Each new block has a unique cryptographic key
resulting from the previous block’s information and key being added into a formula. No participant can change or tamper
with a transaction after it’s been recorded to the shared ledger. If a transaction record includes an error, a new
transaction must be added to reverse the error, and both transactions are then visible.

3. Trust-less

The blockchain is immutable and automates trusted transactions between counterparties who do not need to know each
other. Transactions are only executed when programmed conditions are met by both parties.

4. Unstoppable

Once the conditions programmed into a blockchain protocol are met, an initiated transaction cannot be undone, changed,
or stopped. It’s going to execute and nothing – no bank, government, or third party – can stop it.

5. Lower Cost

In the traditional finance system, we pay third parties like banks to process transactions. The blockchain eliminates
these intermediaries and reduces fees, with some systems returning fees to miners and stakers.

6. Decentralized and Peer-to-Peer (P2P)

![Decentralized and Distributed Network](DistributedNetwork.PNG)

No single entity maintains the network. Unlike centralized banks, decisions on the blockchain are made via consensus.
Decentralization is essential because it ensures people can easily access and build on the platform, and there are
multiple points of failure.

A **peer-to-peer (P2P)** network is a simple network of computers. Here each computer acts as a node for file sharing
within the formed network. Here each node acts as a server and thus there is no central server in the network. This
allows the sharing of a huge amount of data. The tasks are equally divided amongst the nodes. Each node connected in the
network shares an equal workload. For the network to stop working, all the nodes need to individually stop working. This
is because each node works independently.

The P2P architecture of Blockchains provides several benefits, such as greater security compared to traditional
client-server-based networks. A distributed P2P network, paired with a majority of consensus requirement, provides
Blockchains a relatively high degree of resistance to malicious activities.

Cryptocurrencies like Bitcoin, let us send money directly to anyone, anywhere in the world, without an intermediary like
a bank charging transaction or handling fees.

7. Transparent

Public blockchains are open-source software, so anyone can access them to view transactions and their source code. They
can even use the code to build new applications and suggest improvements to the code. Suggestions are accepted or
rejected via consensus.

8. Smart contracts

To speed transactions, a set of rules — called a smart contract — is stored on the blockchain and executed
automatically. A smart contract can define conditions for corporate bond transfers, include terms for travel insurance
to be paid and much more.

9. Universal Banking

Billions of people globally do not have a bank account. Because anyone can access the blockchain to store money, it’s a
great way to bank the unbanked and protect against theft that can happen due to holding cash in physical locations.

#### Disadvantages of a Blockchain

1. Environmental Impact

Blockchain networks like Bitcoin use a lot of electricity to validate transactions, leading to environmental concerns.
For example, Bitcoin consumes more electricity than a small, medium-sized European country, and Bitcoin mining is
threatening China’s climate change goals.

However, many would argue that Bitcoin is held to higher environmental standards than anyone and anything. This may be
true, especially if we consider that the blockchain and Bitcoin are an alternative to the traditional finance system
that uses much more electricity and has a much larger environmental impact. A study by _Galaxy Digital_ suggests Bitcoin
energy consumption is less than half that of the traditional banking system.

2. Personal Responsibility

One of blockchains and cryptocurrencies’ most significant advantages is also its biggest weakness. When we invest in
public open-source blockchains by mining or buying cryptocurrencies and store it in our cryptocurrency wallet (our
wallet is like our bank account, except only we can access it and have the passwords), only we control our money.

We are our own bank— and this is great! But if we lose our seed phrases – the list of words that give us access to
recover our wallets – there is no recourse (compared to banks where we can reset our password). Our money is lost
forever!

Unsurprisingly, a large portion of Bitcoin remains permanently lost. According to some estimates, 20% or 3.7 million of
the currently minted Bitcoin is probably lost forever.

3. Growing Pains

Even though public blockchains remain more efficient than traditional banking systems, **decentralization** comes at the
cost of **scalability**. Trying to grow blockchain networks to global capacity, in turn, is the root cause of speed
inefficiencies. It’s why, Bitcoin and Ethereum can only process a maximum of seven and 30 transactions, respectively,
compared to Visa’s 24,000.

Luckily solutions are being built to improve scalability and the speed of transactions. For example, the lightning
network allows transactions to happen off the Bitcoin blockchain to speed up transactions. On Ethereum, many innovative
Layer 2 (L2) solutions are being developed to improve scalability and speed including rollups, zero-knowledge proofs and
side chains.

4. False Narratives

Some cryptocurrencies are undoubtedly used in unlawful activity. The most famous example is
[Silk Road](https://www.cnbc.com/2019/07/23/man-accused-of-laundering-millions-in-bitcoin-from-silk-road.html): people
laundered money and bought drugs on the platform using Bitcoin.

However, this is no different from the illegal activity that constantly happens when people use other currencies like
the Dollar.

This false narrative that cryptocurrencies are only or mainly used for illicit activities only delays their inevitable
adoption, which can hugely benefit everyone, including the financial system.

---

### Chapter 02 - IntelliJ Project Setup

#### 2.1 JDK, Maven and IntelliJ installation

- **JDK 11 download**: https://jdk.java.net/archive/

  Windows zip: https://download.java.net/java/GA/jdk11/13/GPL/openjdk-11.0.1_windows-x64_bin.zip

- **Maven download**: https://maven.apache.org/download.cgi

  Windows zip: https://dlcdn.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.zip

- **IntelliJ IDEA download**: https://www.jetbrains.com/idea/download/#section=windows

  Windows: https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows&code=IIC

- Set JAVA_HOME, M2_HOME, MAVEN_HOME system variables and set in PATH

#### 2.2 IntelliJ Project Setup - Maven

We can create a Maven project and add required dependencies.

Complete `pom.xml` can be found at Github:
[pom.xml](https://github.com/backstreetbrogrammer/12_BlockchainAndCryptocurrencyInJava/blob/main/pom.xml)

Run Maven Verify command to ensure Maven setup is complete: `mvn verify`

---

### Chapter 03 - Hash Function

**Hashing** is the process of scrambling raw information to the extent that it cannot reproduce it back to its original
form. It takes a piece of information and passes it through a function that performs mathematical operations on the
plaintext. This function is called the **hash function**, and the output is called the **hash value** or **digest**.

![Hash Function](Hash1.PNG)

The **SHA** (Secure Hash Algorithm) is one of the popular cryptographic hash functions. A cryptographic hash can be used
to make a signature for a text or a data file.

**SHA-256** is a part of the [SHA-2](https://en.wikipedia.org/wiki/SHA-2) family of algorithms.

Java supports the following **SHA-2** algorithms:

- SHA-224
- SHA-256
- SHA-384
- SHA-512
- SHA-512/224
- SHA-512/256

The **SHA-256** produces a **256-bit (32 bytes)** output, while **SHA-512** produces a **512-bit (64 bytes)** output.
This is a one-way function, so the result cannot be decrypted back to the original value.

[SHA-3](https://en.wikipedia.org/wiki/SHA-3) is the latest secure hashing standard after **SHA-2**. Compared to SHA-2,
SHA-3 provides a different approach to generate a unique one-way hash, and it can be much faster on some hardware
implementations. Similar to SHA-256, SHA3-256 is the 256-bit fixed-length algorithm in SHA-3.

Java supports the following **SHA-3** algorithms:

- SHA3-224
- SHA3-256
- SHA3-384
- SHA3-512

Blockchain use hash-pointers to reference the previous node in the linked list. Also, each block is assigned a unique
hash value by which the block is identified. Bitcoin uses SHA-256 algorithm.

Hexadecimal can have 16 possible values: [0:9] and [A:F] which can be represented on **4-bits** (2 x 4 = 16). Thus,
SHA-256 algorithm can have an output of **64-character hexadecimal string** (64 x 4 = 256).

Hashing algorithms characteristics:

- **consistent**: same input should always give the same output hash value
- **irreversible**: output hash value cannot be decrypted back to the original input value

A good hash algorithm should be collision-free and a very small input value change should result in a completely
different output hash value.

- Add salt to SHA Hashing

The [salt](https://en.wikipedia.org/wiki/Salt_(cryptography)) is a random data, a technique to prevent
[rainbow attacks](https://en.wikipedia.org/wiki/Rainbow_table). In Java, we can use `SecureRandom` to generate a salt
(random bytes).

---

### Chapter 04 - The Ledger

A ledger is the principal book or computer file for recording and totaling economic transactions measured in terms of a
monetary unit of account by account type.

#### Traditional centralized ledger

As a society, we created ledgers to store information — and they have a variety of applications. For example, we use
ledgers in real estate to store a house’s records, such as when alterations were made or the house was sold. We also use
ledgers in bookkeeping to record all the transactions a company makes.

For example - suppose there are 4 friends in a university. They keep a record of all payments done amongst each other
every day (if any). This record book is kept at a common cupboard and anyone can access it, read it, write it and append
the transactions at the end.

It's good to have a record / ledger like above until they all trust each other but in the real world, records stored
using traditional ledgers are easy to tamper with, meaning we can easily edit, remove, or add a record. As a result,
we’re less likely to trust that the information is accurate.

#### Public keys, Secret Keys and Digital Signature

Digital Signature is a technique for ensuring:

- Integrity: the message hasn't been altered in transit
- Authenticity: the author of the message is really who they claim to be
- Non-repudiation: the author of the message can't later deny that they were the source

![Digital Signature](DigitalSignature.PNG)

##### Sending a message with a Digital Signature

A digital signature is the encrypted hash (digest, checksum) of a message. We generate a hash from a message and encrypt
it with a **private key** according to a chosen algorithm.

A message with its digital signature being sent contains:

- message
- encrypted hash
- corresponding public key
- algorithm

##### Receiving and checking a Digital Signature

To check the digital signature:

- the message receiver generates a new hash from the received message
- decrypts the received encrypted hash using the public key
- compares them
- if they match, the Digital Signature is said to be verified

We should note that we only decrypt the message hash, and not the message itself. In other words, Digital Signature
doesn't try to keep the message secret. Our digital signature only proves that the message was not altered in transit.

When the signature is verified, we're sure that only the owner of the private key could be the author of the message.

#### Decentralized and Distributed ledger

Blockchains solve these problems – and the way we trust – by evolving the traditional bookkeeping model to triple-entry
bookkeeping: transactions on a blockchain are cryptographically sealed by a third entry. This creates a tamper-proof
record of transactions stored in blocks and verified by a distributed consensus mechanism.

![Distributed Ledger](DistributedLedger.PNG)

Each transaction or record on the ledger is stored in a “block.” For example, blocks on the Bitcoin blockchain consist
of an average of more than 500 Bitcoin transactions.

If someone **tampers** the data in a block: the cryptographic hash changes as well and thus the hash pointers between
the blocks are broken. Moreover, the data that has been written to a block cannot be changed or erased as the blocks
are "immutable".

If someone tries to **add** a fake transaction to the blockchain - that will require a **consensus** from the blockchain
miners to approve it and then only add it to the block. As discussed in the digital signature section - this is
impossible to gain consensus on a fake transaction, and it will be disapproved by the miners and not added to the
blockchain.

![Bitcoin Transaction](BitcoinTransactionExample.PNG)

---

### Chapter 05 - Mining

Mining is the process of verifying transactions and recording them onto the public decentralized and distributed ledger,
i.e. Blockchain. There is no centralized authority, any user with mining hardware and internet access can take part.

Mining process is solved based on a difficult mathematical puzzle which requires huge computation power and electricity
to solve and is called **proof-of-work (PoW)**.

The miner who first solves the puzzle gets rewarded.

![Mining example](MiningExample.PNG)

#### Mining Difficulty

Using SHA-256 Hashing algorithm, 1 hash takes 256 bits of memory with 0 or 1 bit value. It means the total number of
hashes possible is 2^256.

The same hash in hexadecimal format takes 64 characters as each character takes 4 bits of memory. It means the total
number of hashes possible is 16^64 which is equal to 2^256.

In Blockchain mining, **difficulty** is defined as **leading zeros** in generated hash. This restricts the total number
of generated SHA-256 hash values possible.

For ex: Suppose predefined condition in the mining of the new block is to have target hash with 3 leading zeros.

It means that miners will keep generating hash until they find the target hash with 3 leading zeros, say,
`0002467734ac34ff79b98da536831fcea82a0fb3f94b34c2e58ab08f041d5d16`

If higher number of leading zeros are required => more difficult is to calculate the hash.

```
Probability of finding hash with 1 leading zero 
= hashes with 1 leading zero / total number of hashes
= 16^63 / 16^64
= 1 / 16
= 6.25%

Probability of finding hash with 2 leading zero 
= hashes with 2 leading zero / total number of hashes
= 16^62 / 16^64
= 1 / 256
= 0.390625%

Probability of finding hash with 3 leading zero 
= hashes with 3 leading zero / total number of hashes
= 16^61 / 16^64
= 1 / 4096
= 0.0244140625%
```

#### NONCE: Number Only Used Once

To generate hash to meet the target hash, miners change the **NONCE** value of the block as all the other data like
block id, transactions, merkle root, previous hash etc. are immutable.

Nonce is a 32-bit unsigned integer (only positive): thus the range is [0 to 2^32-1] or [0 to 4294967295]

When the nonce is changed, miners get a new hash, and then they compare it with target hash (with leading zeros).

If the hash doesn't satisfy the target hash, new nonce is created and continue the hash -> check target.

So, the nonce may be started from 0 and incremented by 1 until the target hash is computed.

```
Block block = new Block();
...
...
int nonce = -1;
String hash = null;
do {
    nonce++;
    block.setNonce(nonce);
    hash = generateHash(block);
} while(!isTargetHashComputed(hash));
```

---

### Chapter 07 - Elliptic Curve Cryptography

Bitcoin uses Elliptic Curve Cryptography because there are some problems with RSA cryptosystem.

#### RSA cryptosystem

RSA (Rivest–Shamir–Adleman) is a public-key cryptosystem that is widely used for secure data transmission. The acronym "
RSA" comes from the surnames of Ron Rivest, Adi Shamir and Leonard Adleman, who publicly described the algorithm in

1977.

In a public-key cryptosystem, the encryption key is public and distinct from the decryption key, which is kept secret
(private). An RSA user creates and publishes a public key based on two large prime numbers, along with an auxiliary
value. The prime numbers are kept secret. Messages can be encrypted by anyone, via the public key, but can only be
decoded by someone who knows the prime numbers.

The security of RSA relies on the practical difficulty of factoring the product of two large prime numbers, the
"factoring problem". Factorization is the trap-door function in RSA but it has never been proven that factorization is
hard. In number theory, the general number field sieve (GNFS) is the most efficient classical algorithm known for
factoring integers larger than 10^100.

There are no published methods to defeat the system if a large enough key is used. Thus, we need huge prime numbers.

RSA is a relatively slow algorithm. Because of this, it is not commonly used to directly encrypt user data. More often,
RSA is used to transmit shared keys for symmetric-key cryptography, which are then used for bulk encryption–decryption.

#### Elliptic Curves

A non-singular elliptic curve has the following features:

- symmetric along x-axis
- a straight line meets the x-axis either at 1 or 3 points

Equation:

```
Let a and b be real numbers.

An elliptic curve E over the field of real numbers R is the set of points (x,y) with x and y in R that satisfy the 
equation: 

y^2 = x^3 + a.x + b

If 
4.a^3 + 27.b^3 = 0
then, elliptic curve is singular which means it does not have 3 distinct roots.
```

Examples:

![Elliptic Curve](EllipticCurve.PNG)

We can use this [link](https://www.desmos.com/calculator/ialhd71we3) to tune around constants a and b and see the
corresponding elliptic curves.


